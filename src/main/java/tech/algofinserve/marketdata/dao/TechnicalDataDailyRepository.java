package tech.algofinserve.marketdata.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalDataDailyRepository
    extends MongoRepository<TechnicalDataDailyRepository, String> {}
