package com.marcura.exchange.rate.schedule;

import com.marcura.exchange.rate.service.exchange.ExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * Business internal api to manage exchange in the persistence store. This interface is framework and vendor agnostics
 */

@Configuration
@EnableScheduling
@Slf4j
public class ExchangeRateScheduler {

    @Autowired
    private ExchangeService exchangeService;

    /**
     * beskryf wat funksie doen
     */
    @Scheduled(cron = "0 5 0 * * ?", zone = "GMT")
    public void fetchLatestExchangeRates() {
        log.info("Exchange Rate Scheduler Activated...");
        log.info("Fetching Latest Exchange Rates...");
        exchangeService.saveLatestExchangeRates(exchangeService.getLatestExchangeRates());
        log.info("Successfully Fetched Latest Exchange Rates...");
    }

}
