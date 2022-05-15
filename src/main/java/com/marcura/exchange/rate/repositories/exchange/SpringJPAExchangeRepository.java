package com.marcura.exchange.rate.repositories.exchange;

import com.marcura.exchange.rate.dto.ExchangeId;
import com.marcura.exchange.rate.entities.ExchangeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ruhancoetzee on May-2022.
 */

/*
 * Spring repository implementation api to manage exchange in the persistence store
 */
@Repository
public interface SpringJPAExchangeRepository extends JpaRepository<ExchangeData, ExchangeId>  {

}
