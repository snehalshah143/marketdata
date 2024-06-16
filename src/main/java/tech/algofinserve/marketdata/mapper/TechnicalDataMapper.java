package tech.algofinserve.marketdata.mapper;

import org.mapstruct.Mapper;
import tech.algofinserve.marketdata.model.domain.TechnicalData;
import tech.algofinserve.marketdata.model.persistable.TechnicalDataPersistable;

@Mapper(componentModel = "spring")
public interface TechnicalDataMapper {

  TechnicalDataPersistable mapDomainToPersistable(TechnicalData technicalData);

  TechnicalData mapPersistableToDomain(TechnicalDataPersistable technicalDataPersistable);
}
