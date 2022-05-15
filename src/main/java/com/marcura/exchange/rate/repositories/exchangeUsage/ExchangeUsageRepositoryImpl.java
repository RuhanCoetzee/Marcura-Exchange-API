package com.marcura.exchange.rate.repositories.exchangeUsage;

import com.marcura.exchange.rate.dto.ExchangeUsage;
import com.marcura.exchange.rate.entities.ExchangeUsageData;
import com.marcura.exchange.rate.mappers.ExchangeUsageMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by ruhancoetzee on May-2022.
 */

/*
 * Business internal api to manage exchange usage in the persistence store
 */
public class ExchangeUsageRepositoryImpl implements ExchangeUsageRepository {

    private final SpringJPAExchangeUsageRepository springJPAExchangeUsageRepository;
    private final ExchangeUsageMapper exchangeUsageMapper;

    public ExchangeUsageRepositoryImpl(SpringJPAExchangeUsageRepository springJPAExchangeUsageRepository, ExchangeUsageMapper exchangeUsageMapper) {
        this.springJPAExchangeUsageRepository = springJPAExchangeUsageRepository;
        this.exchangeUsageMapper = exchangeUsageMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ExchangeUsage> findExchangeUsageByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency, Date date) {
        Optional<ExchangeUsageData> exchangeUsageData = springJPAExchangeUsageRepository.findExchangeUsageByFromCurrencyAndToCurrencyAndDate(fromCurrency, toCurrency, date);
        return exchangeUsageData.map(exchangeUsageMapper::exchangeUsageDataToExchangeUsage);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ExchangeUsage> findExchangeUsageById(Integer id) {
        Optional<ExchangeUsageData> exchangeUsageData = springJPAExchangeUsageRepository.findById(id);
        return exchangeUsageData.map(exchangeUsageMapper::exchangeUsageDataToExchangeUsage);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ExchangeUsage> findAll() {
        return exchangeUsageMapper.exchangeUsageDataToExchangeUsage(springJPAExchangeUsageRepository.findAll());
    }

    @Transactional()
    @Override
    public ExchangeUsage save(ExchangeUsage entity) {
        ExchangeUsageData exchangeUsageData = new ExchangeUsageData();

        if (entity.getId() != null)
            exchangeUsageData = springJPAExchangeUsageRepository.findById(entity.getId()).orElse(new ExchangeUsageData());

        exchangeUsageData.setFromCurrency(entity.getFromCurrency());
        exchangeUsageData.setToCurrency(entity.getToCurrency());
        exchangeUsageData.setDate(entity.getDate());
        exchangeUsageData.setCounter(entity.getCounter());

        springJPAExchangeUsageRepository.save(exchangeUsageData);
        springJPAExchangeUsageRepository.flush();
        return entity;
    }
}