package com.marcura.exchange.rate.configuration;

import com.marcura.exchange.rate.dto.SpreadPercentage;
import com.marcura.exchange.rate.repositories.spreadPercentage.SpreadPercentageRepository;
import com.marcura.exchange.rate.service.spreadPercentage.MaxSpreadPercentageService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * Spread auto configuration to persist required spread percentages to database.
 * On application startup a in memory spread percentages map will be created.
 * This can be used to get the spread percentage for a given currency {@link MaxSpreadPercentageService}. See method getSpread.
 */
@Component
public class SpreadConfiguration {

    private final SpreadPercentageRepository spreadPercentageRepository;

    private Map<String, Double> inMemorySpreadPercentages;

    public SpreadConfiguration(SpreadPercentageRepository spreadPercentageRepository) {
        this.spreadPercentageRepository = spreadPercentageRepository;
    }

    @PostConstruct
    public void loadSpreadPercentagesInMemory() {
        loadDefaultSpreadPercentages();
    }

    public Map<String, Double> getInMemorySpreadPercentages() {
        return inMemorySpreadPercentages;
    }

    // TODO: When the API gets to busy we can refactor this to liquibase
    private void loadDefaultSpreadPercentages(){

        if(spreadPercentageRepository.findAll().isEmpty()){
            SpreadPercentage jpySpread = new SpreadPercentage();
            jpySpread.setCurrency("JPY");
            jpySpread.setSpreadPercentage(3.25);
            spreadPercentageRepository.save(jpySpread);

            SpreadPercentage hkdSpread = new SpreadPercentage();
            hkdSpread.setCurrency("HKD");
            hkdSpread.setSpreadPercentage(3.25);
            spreadPercentageRepository.save(hkdSpread);

            SpreadPercentage krwSpread = new SpreadPercentage();
            krwSpread.setCurrency("KRW");
            krwSpread.setSpreadPercentage(3.25);
            spreadPercentageRepository.save(krwSpread);

            SpreadPercentage myrSpread = new SpreadPercentage();
            myrSpread.setCurrency("MYR");
            myrSpread.setSpreadPercentage(4.50);
            spreadPercentageRepository.save(myrSpread);

            SpreadPercentage inrSpread = new SpreadPercentage();
            inrSpread.setCurrency("INR");
            inrSpread.setSpreadPercentage(4.50);
            spreadPercentageRepository.save(inrSpread);

            SpreadPercentage mxnSpread = new SpreadPercentage();
            mxnSpread.setCurrency("MXN");
            mxnSpread.setSpreadPercentage(4.50);
            spreadPercentageRepository.save(mxnSpread);

            SpreadPercentage rubSpread = new SpreadPercentage();
            rubSpread.setCurrency("RUB");
            rubSpread.setSpreadPercentage(6.00);
            spreadPercentageRepository.save(rubSpread);

            SpreadPercentage cnySpread = new SpreadPercentage();
            cnySpread.setCurrency("CNY");
            cnySpread.setSpreadPercentage(6.00);
            spreadPercentageRepository.save(cnySpread);

            SpreadPercentage zarSpread = new SpreadPercentage();
            zarSpread.setCurrency("ZAR");
            zarSpread.setSpreadPercentage(6.00);
            spreadPercentageRepository.save(zarSpread);
        }

        inMemorySpreadPercentages = spreadPercentageRepository.findAll().stream()
                .collect(Collectors.toMap(SpreadPercentage::getCurrency, SpreadPercentage::getSpreadPercentage));
    }

}
