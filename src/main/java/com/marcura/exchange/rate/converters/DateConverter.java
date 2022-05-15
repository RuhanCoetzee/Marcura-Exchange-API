package com.marcura.exchange.rate.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * Data converter to persist date formats in ISO8601 format to database.
 */
@Converter
public class DateConverter implements AttributeConverter<Date,String> {

    @Override
    public String convertToDatabaseColumn(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    @Override
    public Date convertToEntityAttribute(String date) {
        try{
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        }
        catch(ParseException ex){
            return null;
        }
    }
}
