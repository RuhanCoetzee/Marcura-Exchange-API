package com.marcura.exchange.rate.configuration;

import com.marcura.exchange.rate.mappers.*;
import com.marcura.exchange.rate.repositories.exchange.ExchangeRepository;
import com.marcura.exchange.rate.repositories.exchange.ExchangeRepositoryImpl;
import com.marcura.exchange.rate.repositories.exchange.SpringJPAExchangeRepository;
import com.marcura.exchange.rate.repositories.exchangeUsage.ExchangeUsageRepository;
import com.marcura.exchange.rate.repositories.exchangeUsage.ExchangeUsageRepositoryImpl;
import com.marcura.exchange.rate.repositories.exchangeUsage.SpringJPAExchangeUsageRepository;
import com.marcura.exchange.rate.repositories.spreadPercentage.SpreadPercentageRepository;
import com.marcura.exchange.rate.repositories.spreadPercentage.SpreadPercentageRepositoryImpl;
import com.marcura.exchange.rate.repositories.spreadPercentage.SpringJPASpreadPercentageRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * Spring auto configuration to load required external properties for the exchange rate api
 */

@Configuration
@EntityScan("com.marcura.exchange.rate.entities")
@EnableJpaRepositories("com.marcura.exchange.rate")
public class SpringJpaAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ExchangeRepository exchangeRepository(SpringJPAExchangeRepository springJPAExchangeRepository, ExchangeMapper exchangeMapper) {
        return new ExchangeRepositoryImpl(springJPAExchangeRepository, exchangeMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public ExchangeUsageRepository exchangeUsageRepository(SpringJPAExchangeUsageRepository springJPAExchangeUsageRepository, ExchangeUsageMapper exchangeUsageMapper) {
        return new ExchangeUsageRepositoryImpl(springJPAExchangeUsageRepository, exchangeUsageMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public SpreadPercentageRepository spreadPercentageRepository(SpringJPASpreadPercentageRepository springJPASpreadPercentageRepository, SpreadPercentageMapper spreadPercentageMapper) {
        return new SpreadPercentageRepositoryImpl(springJPASpreadPercentageRepository, spreadPercentageMapper);
    }

}
