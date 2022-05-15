package com.marcura.exchange.rate.service.exchange;

import com.marcura.exchange.rate.api.exceptions.ExchangeRateNotFoundException;
import com.marcura.exchange.rate.api.exceptions.ParameterException;
import com.marcura.exchange.rate.configuration.MarcuraProperties;
import com.marcura.exchange.rate.dto.Exchange;
import com.marcura.exchange.rate.api.responses.ExchangeApiResponse;
import com.marcura.exchange.rate.dto.ExchangeId;
import com.marcura.exchange.rate.dto.ExchangeUsage;
import com.marcura.exchange.rate.repositories.exchange.ExchangeRepository;
import com.marcura.exchange.rate.repositories.exchangeUsage.ExchangeUsageRepository;
import com.marcura.exchange.rate.service.spreadPercentage.MaxSpreadPercentageService;
import com.marcura.exchange.rate.validators.DateParser;
import com.marcura.exchange.rate.validators.DateValidator;
import org.apache.commons.validator.GenericValidator;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ruhancoetzee on May-2022.
 */

/*
 * Business internal api to manage exchange in the persistence store. This interface is framework and vendor agnostics
 */

@Service
public class ExchangeService implements DateValidator, DateParser {

    private static final String currencyCodeRegexPattern = "^[A-Z]{3}$";
    private static final String isoDateFormat = "yyyy-MM-dd";
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(isoDateFormat);

    private final MaxSpreadPercentageService maxSpreadPercentageService;
    private final ExchangeRepository exchangeRepository;
    private final ExchangeUsageRepository exchangeUsageRepository;
    private final MarcuraProperties marcuraProperties;

    private final ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> _locks =
            new ConcurrentHashMap<String, ConcurrentHashMap<String, Object>>();

    public ExchangeService(MaxSpreadPercentageService maxSpreadPercentageService,
                           ExchangeRepository exchangeRepository,
                           ExchangeUsageRepository exchangeUsageRepository,
                           MarcuraProperties marcuraProperties) {

        this.maxSpreadPercentageService = maxSpreadPercentageService;
        this.exchangeRepository = exchangeRepository;
        this.exchangeUsageRepository = exchangeUsageRepository;
        this.marcuraProperties = marcuraProperties;
    }

    public void saveLatestExchangeRates(ExchangeApiResponse latestExchange) {

        var exchangeList = new ArrayList<Exchange>();
        var rateMap = latestExchange.getRates();

        for (String currency : latestExchange.getRates().keySet()) {
            Double rate = rateMap.get(currency);

            var exchange = new Exchange();
            exchange.setId(new ExchangeId(latestExchange.getBase(), currency, toISODate(latestExchange.getDate())));
            exchange.setRate(BigDecimal.valueOf(rate));
            exchange.setLastUpdated(latestExchange.getTimestamp());
            exchangeList.add(exchange);
        }

        exchangeRepository.saveAll(exchangeList);
    }

    /**
     * Calculates the exchange rate based on the concrete implementation which is problem de
     *
     * @param from From currency code in ISO format, i.e. three letters
     * @param to   To currency code in ISO format, i.e. three letters
     * @param date ISO8601 date for the exchange rate
     * @return The exchange rate between the currency pairs on the selected date
     * @see ExchangeService might make use of the {@link MaxSpreadPercentageService}. The calculation of the spread
     * is deferred to the service in question.
     */
    public BigDecimal calculateExchangeRate(String from, String to, String date) {

        if (!from.matches(currencyCodeRegexPattern)) {
            throw new ParameterException("Invalid From Currency Format..");
        }
        if (!to.matches(currencyCodeRegexPattern)) {
            throw new ParameterException("Invalid To Currency Format.");
        }

        Date dateSelected;

        if (Strings.isNotBlank(date) || Strings.isNotEmpty(date)) {
            if (!isValidDate(date)) {
                throw new ParameterException("Invalid Date Format. Expected ISO8601 Date Format.");
            }
            dateSelected = toISODate(date);
        } else {
            dateSelected = currentDateToISODate();
        }

        var baseCurrency = marcuraProperties.getExchange().getBase();
        var fromExchange = exchangeRepository.findExchangeById(new ExchangeId(baseCurrency, from, dateSelected))
                .orElseThrow(() -> new ExchangeRateNotFoundException("The exchange rate for currency " + from + " not found"));
        var toExchange = exchangeRepository.findExchangeById(new ExchangeId(baseCurrency, to, dateSelected))
                .orElseThrow(() -> new ExchangeRateNotFoundException("The exchange rate to currency " + to + " not found"));

        var fromCurrencyRate = fromExchange.getRate();
        var toCurrencyRate = toExchange.getRate();

        var fromCurrencySpread = maxSpreadPercentageService.getSpread(from);
        var toCurrencySpread = maxSpreadPercentageService.getSpread(to);
        var maxSpread = fromCurrencySpread.max(toCurrencySpread);

        var toCurrencyDivideFromCurrency = toCurrencyRate.divide(fromCurrencyRate, MathContext.DECIMAL128);
        var maxSpreadSum = BigDecimal.valueOf(100).subtract(maxSpread).divide(BigDecimal.valueOf(100));
        var exchangeRate = toCurrencyDivideFromCurrency.multiply(maxSpreadSum);

        if (!_locks.containsKey(from)) {
            _locks.put(from, new ConcurrentHashMap<>());
        }

        if (!_locks.get(from).containsKey(to)) {
            _locks.get(from).put(to, new Object());
        }

        /**
         * For maxium perfromnce it can be noted that no two threads should proceed when both threads are concenred bout the same currency pair.
         * i.e. A/P where A is the base currency and P is the secondary currency. However if the two threads in question are concenred with two different currency base pairs
         * i.e. A/B and A/C where A is the base currency and B, C is the secondary currency then both threads can proceed. Therefore the below synchronized code makes use of
         * a two dimensional map of objects to ensure thread safety.
         */
        synchronized (_locks.get(from).get(to)) {
            var exchangeUsage = exchangeUsageRepository.findExchangeUsageByFromCurrencyAndToCurrency(from, to, dateSelected).orElse(new ExchangeUsage(from, to, dateSelected));
            if (exchangeUsage.getId() != null) {
                exchangeUsage.setCounter(exchangeUsage.getCounter() + 1);
            }
            exchangeUsageRepository.save(exchangeUsage);
        }

        return exchangeRate;
    }

    public ExchangeApiResponse getLatestExchangeRates() {
        var headers = new HttpHeaders();
        headers.add("apikey", marcuraProperties.getApi_Key());
        HttpEntity<ExchangeApiResponse> entity = new HttpEntity<ExchangeApiResponse>(headers);

        var restTemplate = new RestTemplate();
        var response = restTemplate.exchange(marcuraProperties.getExchange().getUrl(),
                HttpMethod.GET,
                entity,
                ExchangeApiResponse.class);

        return response.getBody();
    }

    @Override
    public boolean isValidDate(String dateString) {
        return GenericValidator.isDate(dateString, isoDateFormat, true);
    }

    @Override
    public Date currentDateToISODate() {
        try {
            var todayStr = dateFormatter.format(new Date());
            return dateFormatter.parse(todayStr);
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public Date toISODate(String dateString) {
        try {
            return dateFormatter.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
}

