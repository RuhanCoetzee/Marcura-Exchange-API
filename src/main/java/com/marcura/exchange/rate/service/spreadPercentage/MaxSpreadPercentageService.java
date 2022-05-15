package com.marcura.exchange.rate.service.spreadPercentage;

import com.marcura.exchange.rate.configuration.MarcuraProperties;
import com.marcura.exchange.rate.configuration.SpreadConfiguration;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by ruhancoetzee on May-2022.
 */


/**
 * This services implements {@link SpreadPercentageService} and returns the maximum spread between the two currencies.
 * This services also has a fallback default spread which is read from an external configuration file.
 */
@Service
public class MaxSpreadPercentageService implements SpreadPercentageService {

    private final SpreadConfiguration spreadConfiguration;
    private final MarcuraProperties marcuraProperties;

    public MaxSpreadPercentageService(SpreadConfiguration spreadConfiguration, MarcuraProperties marcuraProperties) {
        this.spreadConfiguration = spreadConfiguration;
        this.marcuraProperties = marcuraProperties;
    }

    @Override
    public BigDecimal getSpread(String code) {
        var defaultSpread = BigDecimal.valueOf(marcuraProperties.getExchange().getDefault_spread());
        var lookupSpread = BigDecimal.valueOf(spreadConfiguration.getInMemorySpreadPercentages().getOrDefault(code, 0.0));
        return defaultSpread.max(lookupSpread);
    }
}
