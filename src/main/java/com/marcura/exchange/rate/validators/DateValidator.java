package com.marcura.exchange.rate.validators;

import com.marcura.exchange.rate.service.spreadPercentage.SpreadPercentageService;

import java.util.Date;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * This interface is for date validation
 */
public interface DateValidator {

    boolean isValidDate(String dateString);

}
