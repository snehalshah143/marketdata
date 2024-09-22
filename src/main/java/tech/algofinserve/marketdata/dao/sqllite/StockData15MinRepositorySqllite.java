package tech.algofinserve.marketdata.dao.sqllite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.algofinserve.marketdata.model.persistable.StockData15MinPersistable;

@Repository
public interface StockData15MinRepositorySqllite
    extends JpaRepository<StockData15MinPersistable, String> {}
