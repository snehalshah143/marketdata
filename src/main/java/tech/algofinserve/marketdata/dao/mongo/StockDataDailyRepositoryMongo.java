/*
package tech.algofinserve.marketdata.dao.mongo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tech.algofinserve.marketdata.model.persistable.StockDataDailyPersistable;

//@Repository
public interface StockDataDailyRepositoryMongo
    extends MongoRepository<StockDataDailyPersistable, String> {

  @Query("{symbolId : ?0}")
  List<StockDataDailyPersistable> findStockDataForSymbolKey(String symbolId);
}
*/
