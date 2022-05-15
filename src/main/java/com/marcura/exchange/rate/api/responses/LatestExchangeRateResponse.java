package com.marcura.exchange.rate.api.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marcura.exchange.rate.controller.ExchangeController;
import lombok.Data;

import java.util.Date;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * API success response when user manually triggered get latest exchange rates via API PUT(/exchange) {@link ExchangeController}.
 */

@Data
public class LatestExchangeRateResponse {

    private String message;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;

    public LatestExchangeRateResponse(String message, Date date) {
        this.message = message;
        this.date = date;
    }
}
