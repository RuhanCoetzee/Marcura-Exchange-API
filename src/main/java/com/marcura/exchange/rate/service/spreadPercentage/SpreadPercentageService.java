package com.marcura.exchange.rate.service.spreadPercentage;

import java.math.BigDecimal;

/**
 * Created by ruhancoetzee on May-2022.
 */
public interface SpreadPercentageService {

    BigDecimal getSpread(String code);

}
