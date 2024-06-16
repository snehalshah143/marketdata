package tech.algofinserve.marketdata.mapper;

import org.mapstruct.Mapper;
import tech.algofinserve.marketdata.model.domain.InstrumentTickerAngel;
import tech.algofinserve.marketdata.model.persistable.InstrumentTickerAngelPersistable;

@Mapper(componentModel = "spring")
public interface InstrumentTickerAngelMapper {

  InstrumentTickerAngelPersistable mapDomainToPersistable(
      InstrumentTickerAngel instrumentTickerAngel);

  InstrumentTickerAngel mapPersistableToDomain(
      InstrumentTickerAngelPersistable instrumentTickerAngelPersistable);
}
