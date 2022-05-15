package com.marcura.exchange.rate.mappers;

import com.marcura.exchange.rate.dto.ExchangeUsage;
import com.marcura.exchange.rate.entities.ExchangeUsageData;
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
public interface ExchangeUsageMapper {

    ExchangeUsage exchangeUsageDataToExchangeUsage(ExchangeUsageData exchangeUsageData);

    List<ExchangeUsage> exchangeUsageDataToExchangeUsage(List<ExchangeUsageData> exchangeUsageData);
}
