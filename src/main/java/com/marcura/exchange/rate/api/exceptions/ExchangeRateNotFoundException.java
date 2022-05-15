package com.marcura.exchange.rate.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * TODO
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExchangeRateNotFoundException extends RuntimeException  {

    public ExchangeRateNotFoundException() {
    }

    public ExchangeRateNotFoundException(String message) {
        super(message);
    }

    public ExchangeRateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExchangeRateNotFoundException(Throwable cause) {
        super(cause);
    }

    public ExchangeRateNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
