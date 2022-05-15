package com.marcura.exchange.rate.repositories.exchangeUsage;

import com.marcura.exchange.rate.entities.ExchangeUsageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

/**
 * Created by ruhancoetzee on May-2022.
 */

/*
 * Spring repository implementation api to manage exchange usage in the persistence store
 */
public interface SpringJPAExchangeUsageRepository extends JpaRepository<ExchangeUsageData, Integer> {

    Optional<ExchangeUsageData> findExchangeUsageByFromCurrencyAndToCurrencyAndDate(String fromCurrency, String toCurrency, Date date);

}
