package com.marcura.exchange.rate.entities;

import com.marcura.exchange.rate.dto.ExchangeId;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * TODO
 * @author ruhancoetzee
 * */
@Data
@Entity
@Table(name = "exchange")
public class ExchangeData implements Serializable {

    @EmbeddedId
    private ExchangeId id;
    private Integer lastUpdated;
    private BigDecimal rate;

}
