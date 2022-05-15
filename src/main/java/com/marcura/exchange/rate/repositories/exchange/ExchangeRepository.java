package com.marcura.exchange.rate.repositories.exchange;

import com.marcura.exchange.rate.dto.Exchange;
import com.marcura.exchange.rate.dto.ExchangeId;

import java.util.List;
import java.util.Optional;

/**
 * Created by ruhancoetzee on May-2022.
 */

/*
 * Business internal api to manage exchange in the persistence store. This interface is framework and vendor agnostics
 */
public interface ExchangeRepository  {

    Optional<Exchange> findExchangeById(ExchangeId id);

    List<Exchange> findAll();

    Exchange save(Exchange entity);

    void saveAll(List<Exchange> entities);

}
