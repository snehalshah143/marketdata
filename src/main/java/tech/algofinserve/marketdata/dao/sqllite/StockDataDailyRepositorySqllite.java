package tech.algofinserve.marketdata.dao.sqllite;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.algofinserve.marketdata.model.persistable.StockDataDailyPersistable;

@Repository
public interface StockDataDailyRepositorySqllite
    extends JpaRepository<StockDataDailyPersistable, String> {

  List<StockDataDailyPersistable> findAllBySymbolId(String symbolId);
}
