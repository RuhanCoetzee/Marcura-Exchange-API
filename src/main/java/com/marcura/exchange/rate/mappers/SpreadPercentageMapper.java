package com.marcura.exchange.rate.mappers;

import com.marcura.exchange.rate.dto.SpreadPercentage;
import com.marcura.exchange.rate.entities.SpreadPercentageData;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * TODO
 * @author ruhancoetzee
 * */
@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SpreadPercentageMapper {

    SpreadPercentage spreadPercentageDataToSpreadPercentage(SpreadPercentageData spreadPercentageData);

    List<SpreadPercentage> spreadPercentageDataToSpreadPercentage(List<SpreadPercentageData> spreadPercentageData);

}
