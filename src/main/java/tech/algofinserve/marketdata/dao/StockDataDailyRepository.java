package tech.algofinserve.marketdata.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.algofinserve.marketdata.model.persistable.StockDataDailyPersistable;

@Repository
public interface StockDataDailyRepository
    extends MongoRepository<StockDataDailyPersistable, String> {

  //    @Query("{exchSeg : ?0}")
  //    List<StockDataDailyPersistable> findStockDataForSymbolKey(SymbolKeyPersistable
  // symbolKeyPersistable);
}
