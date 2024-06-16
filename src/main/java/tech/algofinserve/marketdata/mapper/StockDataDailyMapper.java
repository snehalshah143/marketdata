package tech.algofinserve.marketdata.mapper;

import org.mapstruct.Mapper;
import tech.algofinserve.marketdata.model.domain.StockDataDaily;
import tech.algofinserve.marketdata.model.persistable.StockDataDailyPersistable;

@Mapper(componentModel = "spring")
public interface StockDataDailyMapper {

  StockDataDailyPersistable mapDomainToPersistable(StockDataDaily stockDataDaily);

  StockDataDaily mapPersistableToDomain(StockDataDailyPersistable stockDataDailyPersistable);
}
