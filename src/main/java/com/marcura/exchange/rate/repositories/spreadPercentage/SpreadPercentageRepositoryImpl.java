package com.marcura.exchange.rate.repositories.spreadPercentage;

import com.marcura.exchange.rate.dto.SpreadPercentage;
import com.marcura.exchange.rate.entities.SpreadPercentageData;
import com.marcura.exchange.rate.mappers.SpreadPercentageMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by ruhancoetzee on May-2022.
 */

/*
 * Business internal api to manage spread percentage in the persistence store
 */
public class SpreadPercentageRepositoryImpl implements SpreadPercentageRepository {

    private final SpringJPASpreadPercentageRepository springJPASpreadPercentageRepository;
    private final SpreadPercentageMapper spreadPercentageMapper;

    public SpreadPercentageRepositoryImpl(SpringJPASpreadPercentageRepository springJPASpreadPercentageRepository, SpreadPercentageMapper spreadPercentageMapper) {
        this.springJPASpreadPercentageRepository = springJPASpreadPercentageRepository;
        this.spreadPercentageMapper = spreadPercentageMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<SpreadPercentage> findSpreadPercentageByCurrency(String currency) {
        return Optional.of(spreadPercentageMapper.spreadPercentageDataToSpreadPercentage(springJPASpreadPercentageRepository.findById(currency).orElse(null)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<SpreadPercentage> findAll() {
        return spreadPercentageMapper.spreadPercentageDataToSpreadPercentage(springJPASpreadPercentageRepository.findAll());
    }

    @Transactional()
    @Override
    public SpreadPercentage save(SpreadPercentage entity) {

        SpreadPercentageData spreadPercentageData = new SpreadPercentageData();

        if (entity.getCurrency() != null){
            spreadPercentageData = springJPASpreadPercentageRepository.findById(entity.getCurrency()).orElse(new SpreadPercentageData());
        }

        spreadPercentageData.setCurrency(entity.getCurrency());
        spreadPercentageData.setSpreadPercentage(entity.getSpreadPercentage());

        springJPASpreadPercentageRepository.save(spreadPercentageData);
        springJPASpreadPercentageRepository.flush();

        return entity;
    }
}
