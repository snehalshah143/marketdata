package tech.algofinserve.marketdata.dao;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tech.algofinserve.marketdata.model.persistable.InstrumentTickerAngelPersistable;

@Repository
public interface InstrumentTickerAngelRepository
    extends MongoRepository<InstrumentTickerAngelPersistable, Long> {

  @Query("{exchSeg : ?0}")
  List<InstrumentTickerAngelPersistable> findInstrumentTickerByExchangeSegment(
      String exchangeSegment);
}
