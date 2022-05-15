package com.marcura.exchange.rate.dto;

import lombok.Data;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * This data object is for spread percentage data.
 * */

@Data
public class SpreadPercentage {

    private String currency;
    private Double spreadPercentage;

}
