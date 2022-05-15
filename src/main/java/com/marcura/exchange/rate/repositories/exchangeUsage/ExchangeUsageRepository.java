package com.marcura.exchange.rate.repositories.exchangeUsage;

import com.marcura.exchange.rate.dto.ExchangeUsage;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by ruhancoetzee on May-2022.
 */
public interface ExchangeUsageRepository {

    Optional<ExchangeUsage> findExchangeUsageByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency, Date date);

    Optional<ExchangeUsage> findExchangeUsageById(Integer id);

    List<ExchangeUsage> findAll();

    ExchangeUsage save(ExchangeUsage entity);
}
