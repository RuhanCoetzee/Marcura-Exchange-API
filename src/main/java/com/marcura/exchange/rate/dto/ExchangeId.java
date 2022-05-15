package com.marcura.exchange.rate.dto;

import com.marcura.exchange.rate.converters.DateConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * ExchangeID is the id mapping to the Exchange table.
 * */

@Data
@NoArgsConstructor
@Embeddable
public class ExchangeId implements Serializable {

    @Column(name = "base")
    private String base;
    @Column(name = "currency")
    private String currency;
    @Column(name = "date")
    @Convert(converter = DateConverter.class)
    private Date date;

    public ExchangeId(String base, String currency, Date date) {
        this.base = base;
        this.currency = currency;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeId that = (ExchangeId) o;
        return Objects.equals(base, that.base) && Objects.equals(currency, that.currency) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(base, currency, date);
    }
}
