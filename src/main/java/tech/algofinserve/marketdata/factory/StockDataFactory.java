package tech.algofinserve.marketdata.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import tech.algofinserve.marketdata.constants.CandleTimeFrame;
import tech.algofinserve.marketdata.dao.sqllite.StockData15MinRepositorySqllite;
import tech.algofinserve.marketdata.dao.sqllite.StockDataDailyRepositorySqllite;
import tech.algofinserve.marketdata.model.domain.StockData;
import tech.algofinserve.marketdata.model.domain.StockData15Min;
import tech.algofinserve.marketdata.model.domain.StockDataDaily;

@Component
public class StockDataFactory {

  /*  @Value("${db.type}")
  private String dbType;*/
  /*  @Autowired
  StockData15MinRepositoryMongo stockData15MinRepositoryMongo;
  @Autowired
  StockDataDailyRepositoryMongo stockDataDailyRepositoryMongo;*/

  @Autowired StockData15MinRepositorySqllite stockData15MinRepositorySqllite;
  @Autowired StockDataDailyRepositorySqllite stockDataDailyRepositorySqllite;

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

  /*  public MongoRepository getStockDataRepositoryMongo(CandleTimeFrame candleTimeFrame) {

    switch (candleTimeFrame) {
      case ONE_DAY:
        return stockDataDailyRepositoryMongo;
      case FIFTEEN_MINUTE:
        return stockData15MinRepositoryMongo;

      default:
        throw new IllegalArgumentException("Unknown timeFrame " + candleTimeFrame);
    }
  }*/

  public JpaRepository getStockDataRepositorySqlite(CandleTimeFrame candleTimeFrame) {

    switch (candleTimeFrame) {
      case ONE_DAY:
        return stockDataDailyRepositorySqllite;
      case FIFTEEN_MINUTE:
        return stockData15MinRepositorySqllite;

      default:
        throw new IllegalArgumentException("Unknown timeFrame " + candleTimeFrame);
    }
  }
}
