package tech.algofinserve.marketdata.model.persistable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stock_data_15min")
public class StockData15MinPersistable extends StockDataPersistable {
  public StockData15MinPersistable() {
    //     super(CandleTimeFrame.FIFTEEN_MINUTE);
  }
}
