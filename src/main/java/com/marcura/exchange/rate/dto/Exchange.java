package com.marcura.exchange.rate.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * TODO
 * @author ruhancoetzee
 * */
@Data
public class Exchange {

    private ExchangeId id;
    private Integer lastUpdated;
    private BigDecimal rate;

}
