package tech.algofinserve.marketdata.mapper;

import org.mapstruct.Mapper;
import tech.algofinserve.marketdata.model.domain.StockData15Min;
import tech.algofinserve.marketdata.model.persistable.StockData15MinPersistable;

@Mapper(componentModel = "spring")
public interface StockData15MinMapper {

  StockData15MinPersistable mapDomainToPersistable(StockData15Min stockData15Min);

  StockData15Min mapPersistableToDomain(StockData15MinPersistable stockData15MinPersistable);
}
