package tech.algofinserve.marketdata.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import tech.algofinserve.marketdata.constants.CandleTimeFrame;
import tech.algofinserve.marketdata.model.domain.Candle;

public class HistoricalDataServiceImpl {

  // Stock Code as Key
  public static Map<String, Map<CandleTimeFrame, Candle>> historicalStockPrices =
      new ConcurrentHashMap<>();
}
