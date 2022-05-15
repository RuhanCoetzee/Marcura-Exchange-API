package com.marcura.exchange.rate.api.responses;

import lombok.Data;

import java.math.BigDecimal;

/**
 * TODO
 */

@Data
public class CalculatedExchangeRateResponse {

    private String from;
    private String to;
    private BigDecimal exchange;

    public CalculatedExchangeRateResponse(String from, String to, BigDecimal exchange) {
        this.from = from;
        this.to = to;
        this.exchange = exchange;
    }
}