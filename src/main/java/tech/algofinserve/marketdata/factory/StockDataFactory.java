package tech.algofinserve.marketdata.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import tech.algofinserve.marketdata.constants.CandleTimeFrame;
import tech.algofinserve.marketdata.dao.StockData15MinRepository;
import tech.algofinserve.marketdata.dao.StockDataDailyRepository;
import tech.algofinserve.marketdata.model.domain.StockData;
import tech.algofinserve.marketdata.model.domain.StockData15Min;
import tech.algofinserve.marketdata.model.domain.StockDataDaily;

@Component
public class StockDataFactory {
  @Autowired StockData15MinRepository stockData15MinRepository;
  @Autowired StockDataDailyRepository stockDataDailyRepository;

  public static StockData getStockDataObject(CandleTimeFrame candleTimeFrame) {

    switch (candleTimeFrame) {
      case ONE_DAY:
        return new StockDataDaily();
      case FIFTEEN_MINUTE:
        return new StockData15Min();

      default:
        throw new IllegalArgumentException("Unknown channel " + candleTimeFrame);
    }
  }

  public static Long getStockDataCandleNumber(CandleTimeFrame candleTimeFrame) {

    switch (candleTimeFrame) {
      case ONE_DAY:
        return 1L;
      case FIFTEEN_MINUTE:
        return 2L;

      default:
        throw new IllegalArgumentException("Unknown channel " + candleTimeFrame);
    }
  }

  public MongoRepository getStockDataRepository(CandleTimeFrame candleTimeFrame) {

    switch (candleTimeFrame) {
      case ONE_DAY:
        return stockDataDailyRepository;
      case FIFTEEN_MINUTE:
        return stockData15MinRepository;

      default:
        throw new IllegalArgumentException("Unknown timeFrame " + candleTimeFrame);
    }
  }
}
