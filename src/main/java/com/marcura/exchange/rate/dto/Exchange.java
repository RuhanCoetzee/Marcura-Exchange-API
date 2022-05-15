package com.marcura.exchange.rate.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * This data object is for exchange data
 * */

@Data
public class Exchange {

    private ExchangeId id;
    private Integer lastUpdated;
    private BigDecimal rate;

}
