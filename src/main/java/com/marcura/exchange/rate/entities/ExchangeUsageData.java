package com.marcura.exchange.rate.entities;

import com.marcura.exchange.rate.converters.DateConverter;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 *  This data class is for exchange table.
 * */

@Data
@Entity
@Table(name = "exchange_usage")
public class ExchangeUsageData  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fromCurrency;
    private String toCurrency;
    @Convert(converter = DateConverter.class)
    private Date date;
    private Integer counter;
}
