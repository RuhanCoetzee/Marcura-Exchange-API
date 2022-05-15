package com.marcura.exchange.rate.repositories.spreadPercentage;

import com.marcura.exchange.rate.dto.SpreadPercentage;

import java.util.List;
import java.util.Optional;

/**
 * Created by ruhancoetzee on May-2022.
 */
public interface SpreadPercentageRepository {

    Optional<SpreadPercentage> findSpreadPercentageByCurrency(String currency);

    List<SpreadPercentage> findAll();

    SpreadPercentage save(SpreadPercentage entity);
}
