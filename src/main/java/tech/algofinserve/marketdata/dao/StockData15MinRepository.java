package tech.algofinserve.marketdata.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.algofinserve.marketdata.model.persistable.StockData15MinPersistable;

@Repository
public interface StockData15MinRepository
    extends MongoRepository<StockData15MinPersistable, Long> {}
