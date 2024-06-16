package tech.algofinserve.marketdata.service;

import com.angelbroking.smartapi.SmartConnect;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.ta4j.core.Bar;
import tech.algofinserve.marketdata.constants.CandleTimeFrame;
import tech.algofinserve.marketdata.model.domain.StockData;
import tech.algofinserve.marketdata.model.domain.Ticker;

public interface AngelMarketDataService {

  Set<StockData> getHistoricalDataForTicker(
      SmartConnect smartConnect,
      Ticker ticker,
      CandleTimeFrame candleTimeFrame,
      String fromDate,
      String toDate);

  void addStockDataToStore(
      Ticker ticker, CandleTimeFrame candleTimeFrame, Set<StockData> stockDataList);

  Set<StockData> getStockDataForTimeFrame(String stockSymbol, CandleTimeFrame candleTimeFrame);

  Set<StockData> getStockDataForRange(
      String stockSymbol, CandleTimeFrame candleTimeFrame, Date fromDate, Date toDate);

  public List<Bar> getBarSeries(String stockSymbol, CandleTimeFrame candleTimeFrame);

  public List<Bar> getBarSeriesForRange(
      String stockSymbol, CandleTimeFrame candleTimeFrame, Date fromDate, Date toDate);
}
