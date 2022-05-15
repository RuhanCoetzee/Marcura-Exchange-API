package com.marcura.exchange.rate.api.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.LinkedHashMap;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * TODO
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
