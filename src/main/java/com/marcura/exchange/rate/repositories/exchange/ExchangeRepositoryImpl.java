package com.marcura.exchange.rate.repositories.exchange;

import com.marcura.exchange.rate.dto.Exchange;
import com.marcura.exchange.rate.dto.ExchangeId;
import com.marcura.exchange.rate.entities.ExchangeData;
import com.marcura.exchange.rate.mappers.ExchangeMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by ruhancoetzee on May-2022.
 */

/*
 * Business internal api to manage exchange in the persistence store
 */
public class ExchangeRepositoryImpl implements ExchangeRepository {

    private final SpringJPAExchangeRepository springJPAExchangeRepository;
    private final ExchangeMapper exchangeMapper;

    public ExchangeRepositoryImpl(SpringJPAExchangeRepository springJPAExchangeRepository, ExchangeMapper exchangeMapper) {
        this.springJPAExchangeRepository = springJPAExchangeRepository;
        this.exchangeMapper = exchangeMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Exchange> findExchangeById(ExchangeId id) {
        Optional<ExchangeData> exchangeData = springJPAExchangeRepository.findById(id);
        return exchangeData.map(exchangeMapper::exchangeDataToExchange);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Exchange> findAll() {
        return exchangeMapper.exchangeDataToExchange(springJPAExchangeRepository.findAll());
    }

    @Transactional()
    @Override
    public Exchange save(Exchange entity) {

        ExchangeData exchangeData = new ExchangeData();

        if (entity.getId() != null) {
            exchangeData = springJPAExchangeRepository.findById(entity.getId()).orElse(new ExchangeData());
        }

        exchangeData.setId(entity.getId());
        exchangeData.setRate(entity.getRate());
        exchangeData.setLastUpdated(entity.getLastUpdated());

        springJPAExchangeRepository.save(exchangeData);
        springJPAExchangeRepository.flush();

        return entity;
    }

    @Transactional()
    @Override
    public void saveAll(List<Exchange> entities) {
        entities.forEach(this::save);
    }
}
