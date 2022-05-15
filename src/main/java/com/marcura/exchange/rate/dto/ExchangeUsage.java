package com.marcura.exchange.rate.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * This data object is for exchange usage data.
 * */

@Data
@NoArgsConstructor
public class ExchangeUsage {

    private Integer id;
    private String fromCurrency;
    private String toCurrency;
    private Date date;
    private Integer counter;

    public ExchangeUsage(String fromCurrency, String toCurrency, Date date) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.date = date;
        this.counter = 1;
    }
}
