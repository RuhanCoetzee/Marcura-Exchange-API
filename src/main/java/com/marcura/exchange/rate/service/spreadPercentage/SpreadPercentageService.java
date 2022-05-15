package com.marcura.exchange.rate.service.spreadPercentage;

import java.math.BigDecimal;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * This interface is for methods to be implemented in @{link MaxSpreadPercentageService}
 */
public interface SpreadPercentageService {

    BigDecimal getSpread(String code);

}
