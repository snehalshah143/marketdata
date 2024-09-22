package tech.algofinserve.marketdata.model.persistable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

// @Document(collection = "stock_data_15min")
@Entity
@Table(
    name = "stock_data_15min",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"symbolId", "timestamp"})})
public class StockData15MinPersistable extends StockDataPersistable {
  public StockData15MinPersistable() {
    //     super(CandleTimeFrame.FIFTEEN_MINUTE);
  }
}
