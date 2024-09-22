package tech.algofinserve.marketdata.model.persistable;

// import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

// @Document(collection = "stock_data_daily")
@Entity
@Table(
    name = "stock_data_daily",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"symbolId", "timestamp"})})
public class StockDataDailyPersistable extends StockDataPersistable {
  public StockDataDailyPersistable() {
    //      super(CandleTimeFrame.ONE_DAY);
  }
}
