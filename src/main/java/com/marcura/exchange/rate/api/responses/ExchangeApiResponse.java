package com.marcura.exchange.rate.api.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marcura.exchange.rate.controller.ExchangeController;
import lombok.Data;

import java.util.LinkedHashMap;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * Data Object to map API GET(/exchange){@link ExchangeController} response to.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ExchangeApiResponse {

    private Boolean success;
    private Integer timestamp;
    private String base;
    private String date;
    private LinkedHashMap<String, Double> rates;

}
