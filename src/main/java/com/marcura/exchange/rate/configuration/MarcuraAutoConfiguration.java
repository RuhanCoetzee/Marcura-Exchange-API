package com.marcura.exchange.rate.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * Spring auto configuration to load required external properties for the exchange rate api
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
@EnableConfigurationProperties(MarcuraProperties.class)
public class MarcuraAutoConfiguration {

    private final MarcuraProperties marcuraProperties;

    public MarcuraAutoConfiguration(MarcuraProperties marcuraProperties) {
        this.marcuraProperties = marcuraProperties;
    }
}
