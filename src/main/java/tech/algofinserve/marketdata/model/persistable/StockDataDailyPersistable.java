package tech.algofinserve.marketdata.model.persistable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stock_data_daily")
public class StockDataDailyPersistable extends StockDataPersistable {
  public StockDataDailyPersistable() {
    //      super(CandleTimeFrame.ONE_DAY);
  }
}
