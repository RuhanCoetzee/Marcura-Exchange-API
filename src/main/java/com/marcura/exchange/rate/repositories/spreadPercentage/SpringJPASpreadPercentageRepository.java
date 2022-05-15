package com.marcura.exchange.rate.repositories.spreadPercentage;

import com.marcura.exchange.rate.entities.SpreadPercentageData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruhancoetzee on May-2022.
 */

/*
 * Spring repository implementation api to manage spread percentage in the persistence store
 */
public interface SpringJPASpreadPercentageRepository extends JpaRepository<SpreadPercentageData, String> {
}
