package com.marcura.exchange.rate.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * API Exception response for invalid parameters.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ParameterException extends RuntimeException {

    public ParameterException() {
    }

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterException(Throwable cause) {
        super(cause);
    }

    public ParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
