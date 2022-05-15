package com.marcura.exchange.rate.repositories.spreadPercentage;

import com.marcura.exchange.rate.dto.SpreadPercentage;

import java.util.List;
import java.util.Optional;

/**
 * Created by ruhancoetzee on May-2022.
 */

/*
 * Business internal api to manage exchange in the persistence store. This interface is framework and vendor agnostics
 */
public interface SpreadPercentageRepository {

    Optional<SpreadPercentage> findSpreadPercentageByCurrency(String currency);

    List<SpreadPercentage> findAll();

    SpreadPercentage save(SpreadPercentage entity);
}
