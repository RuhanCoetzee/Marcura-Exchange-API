package com.marcura.exchange.rate.validators;

import java.util.Date;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * This interface is for date parsing
 */
public interface DateParser {

    Date currentDateToISODate();

    Date toISODate(String dateString);

}
