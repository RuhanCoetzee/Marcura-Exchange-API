package com.marcura.exchange.rate.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 *  This data class is for spread percentage table.
 * */

@Data
@Entity
@Table(name = "spread_percentage")
public class SpreadPercentageData  implements Serializable {

    @Id
    private String currency;
    private Double spreadPercentage;

}
