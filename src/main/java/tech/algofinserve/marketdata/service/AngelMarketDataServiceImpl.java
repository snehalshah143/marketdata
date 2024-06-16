package tech.algofinserve.marketdata.service;

import com.angelbroking.smartapi.SmartConnect;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeries;
import tech.algofinserve.marketdata.constants.CandleTimeFrame;
import tech.algofinserve.marketdata.constants.ExchangeSegment;
import tech.algofinserve.marketdata.constants.InstrumentType;
import tech.algofinserve.marketdata.dao.StockDataDailyRepository;
import tech.algofinserve.marketdata.factory.StockDataFactory;
import tech.algofinserve.marketdata.mapper.StockDataDailyMapper;
import tech.algofinserve.marketdata.model.domain.*;
import tech.algofinserve.marketdata.model.persistable.StockDataDailyPersistable;
import tech.algofinserve.marketdata.model.persistable.StockDataPersistable;
import tech.algofinserve.marketdata.util.SymbolKeyUtil;

@Service
public class AngelMarketDataServiceImpl implements AngelMarketDataService {

  @Autowired StockDataFactory stockDataFactory;
  @Autowired StockDataDailyRepository stockDataDailyRepository;

  @Autowired StockDataDailyMapper stockDataDailyMapper;

  // StockName vrs Map<TimeFrame,StockData>
  private Map<String, Map<CandleTimeFrame, Set<StockData>>> stockPricesStore =
      new ConcurrentHashMap<>();
  // Bar series name is _symbol_timeframe_exchange_instrumenttype
  // Map<String, BarSeries>

  private Map<String, Map<CandleTimeFrame, BarSeries>> barSeriesMap = new ConcurrentHashMap<>();
  SimpleDateFormat simpleDateFormatAngel = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
  //   @Autowired
  //   ConversionUtil conversionUtil;

  public StockData getStockDataForTicker(
      SmartConnect smartConnect, Ticker ticker, CandleTimeFrame candleTimeFrame, String timeStamp) {
    String symbolId =
        SymbolKeyUtil.getSymbolId(
            "RELIANCE", CandleTimeFrame.ONE_DAY, InstrumentType.EQ, ExchangeSegment.NSE);
    Optional<StockDataDailyPersistable> stockDataDailyPersistable =
        stockDataDailyRepository.findById(symbolId);
    stockDataDailyPersistable.ifPresent(p -> System.out.println(p.toString()));
    if (stockDataDailyPersistable.isPresent()) {
      return stockDataDailyMapper.mapPersistableToDomain(stockDataDailyPersistable.get());
    }
    return null;
  }

  public Set<StockData> getHistoricalDataForTicker(
      SmartConnect smartConnect,
      Ticker ticker,
      CandleTimeFrame candleTimeFrame,
      String fromDate,
      String toDate) {
    /*
     JSONObject requestObejct = new JSONObject();

    requestObejct.put("exchange", "NSE");
     requestObejct.put("symboltoken", "3045");
     requestObejct.put("interval", "ONE_MINUTE");
     requestObejct.put("fromdate", "2023-12-29 09:00");
     requestObejct.put("todate", "2023-12-29 09:20");*/

    JSONObject requestObject =
        constructHistoricalDataRequest(ticker, candleTimeFrame, fromDate, toDate);
    JSONArray result = smartConnect.candleDataAsJSONArray(requestObject);

    Set<StockData> stockDataDailyList =
        convertRawDataToStockDataList(result, ticker, candleTimeFrame);
    addStockDataToStore(ticker, CandleTimeFrame.ONE_DAY, stockDataDailyList);

    return stockDataDailyList;
  }

  private Set<StockData> convertRawDataToStockDataList(
      JSONArray result, Ticker ticker, CandleTimeFrame candleTimeFrame) {
    Set<StockData> stockDataList = new LinkedHashSet<>();

    if (result != null && result.length() > 0) {
      for (int i = 0; i < result.length(); i++) {
        StockData stockData = StockDataFactory.getStockDataObject(candleTimeFrame);

        JSONArray array = result.getJSONArray(i);
        populateBasicStockData(ticker, candleTimeFrame, stockData, array);
        populateOHLCVStockData(stockData, array);
        populateTechnicalData(stockData);
        stockDataList.add(stockData);
      }
    }
    return stockDataList;
  }

  private void populateTechnicalData(StockData stockData) {
    TechnicalData technicalData = new TechnicalData();

    technicalData.setSymbolId(stockData.getSymbolId());
    technicalData.setTimestamp(stockData.getTimestamp());
    technicalData.setEma8(21d);
    stockData.setTechnicalData(technicalData);
  }

  private void populateOHLCVStockData(StockData stockData, JSONArray array) {

    stockData.setOpen(Double.valueOf(array.get(1).toString()));
    stockData.setHigh(Double.valueOf(array.get(2).toString()));
    stockData.setLow(Double.valueOf(array.get(3).toString()));
    stockData.setClose(Double.valueOf(array.get(4).toString()));
    stockData.setVolume(Long.valueOf(array.get(5).toString()));

    //   String id=new
    // StringBuilder(stockData.getSymbol()).append(stockData.candleTimeFrame.value()).append(stockData.getTimestamp()).toString();
    //   stockData.setId(id);
  }

  private void populateBasicStockData(
      Ticker ticker, CandleTimeFrame candleTimeFrame, StockData stockData, JSONArray array) {
    LocalDateTime timestamp = getLocalDateTime(simpleDateFormatAngel, array.get(0).toString());
    String symbolId =
        SymbolKeyUtil.getSymbolId(
            ticker.getStockSymbol(),
            candleTimeFrame,
            ticker.getInstrumentType(),
            ticker.getExchangeSegment());
    stockData.setSymbolId(symbolId);
    stockData.setTimestamp(timestamp);
    //        stockData.setCandleNum(StockDataFactory.getStockDataCandleNumber(candleTimeFrame));

  }

  private static LocalDateTime getLocalDateTime(SimpleDateFormat simpleFormat, String dateString) {

    dateString = dateString.split("\\+")[0];

    LocalDateTime localDateTime = LocalDateTime.parse(dateString);

    return localDateTime;
  }

  private Date getDate(SimpleDateFormat simpleFormat, String dateString) {
    Date date;
    dateString = dateString.split("\\+")[0];

    try {
      date = simpleFormat.parse(dateString);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
    return date;
  }

  private JSONObject constructHistoricalDataRequest(
      Ticker ticker, CandleTimeFrame candleTimeFrame, String fromDate, String toDate) {
    // String  dateFormatForRequest="yyyy-mm-dd hh:mm";
    JSONObject requestObject = new JSONObject();
    requestObject.put("exchange", ticker.getExchangeSegment().value());
    requestObject.put("symboltoken", ticker.getToken());
    requestObject.put("interval", candleTimeFrame.name());
    requestObject.put("fromdate", fromDate);
    requestObject.put("todate", toDate);
    return requestObject;
  }

  public void addStockDataToStore(
      Ticker ticker, CandleTimeFrame candleTimeFrame, Set<StockData> stockDataList) {

    if (stockPricesStore.containsKey(ticker.getStockSymbol())) {
      Map<CandleTimeFrame, Set<StockData>> stockDataMap =
          stockPricesStore.get(ticker.getStockSymbol());

      if (stockDataMap.containsKey(candleTimeFrame)) {
        stockDataMap.get(candleTimeFrame).addAll(stockDataList);
      } else {
        stockDataMap.put(candleTimeFrame, stockDataList);
      }
    } else {
      Map<CandleTimeFrame, Set<StockData>> stockDataMap = new ConcurrentHashMap<>();
      stockDataMap.put(candleTimeFrame, stockDataList);
      stockPricesStore.put(ticker.getStockSymbol(), stockDataMap);
    }

    saveStockData(candleTimeFrame, stockDataList);
    // Commented temporary
    //    addStockDataToBarSeriesMap(ticker,candleTimeFrame,stockDataList);
  }

  private void addStockDataToBarSeriesMap(
      Ticker ticker, CandleTimeFrame candleTimeFrame, Set<StockData> stockDataList) {

    BarSeries barSeries = convertBarSeriesFromStockData(ticker, candleTimeFrame, stockDataList);

    if (barSeriesMap.containsKey(ticker.getStockSymbol())) {
      Map<CandleTimeFrame, BarSeries> barDataMap = barSeriesMap.get(ticker.getStockSymbol());

      if (barDataMap.containsKey(candleTimeFrame)) {
        BarSeries existingBarSeries = barDataMap.get(candleTimeFrame);
        barSeries.getBarData().stream().forEach(p -> existingBarSeries.addBar(p));

      } else {
        barDataMap.put(candleTimeFrame, barSeries);
      }
    } else {
      Map<CandleTimeFrame, BarSeries> barDataMap = new ConcurrentHashMap<>();
      barDataMap.put(candleTimeFrame, barSeries);
      barSeriesMap.put(ticker.getStockSymbol(), barDataMap);
    }
  }

  private BarSeries convertBarSeriesFromStockData(
      Ticker ticker, CandleTimeFrame candleTimeFrame, Set<StockData> stockDataList) {
    String barSeriesName = getBarSeriesName(ticker, candleTimeFrame);
    BarSeries baseBarSeries = new BaseBarSeries(barSeriesName);

    stockDataList.stream()
        .forEach(
            p -> {
              ZonedDateTime dateTime = ZonedDateTime.of(p.getTimestamp(), ZoneId.systemDefault());
              /*        double open = Double.parseDouble(line[1]);
              double high = Double.parseDouble(line[2]);
              double low = Double.parseDouble(line[3]);
              double close = Double.parseDouble(line[4]);
              double volume = Double.parseDouble(line[5]);*/
              baseBarSeries.addBar(
                  dateTime, p.getOpen(), p.getHigh(), p.getLow(), p.getClose(), p.getVolume());
            });

    return baseBarSeries;
  }

  @NotNull
  private static String getBarSeriesName(Ticker ticker, CandleTimeFrame candleTimeFrame) {
    return new StringBuilder(ticker.getStockSymbol())
        .append("_")
        .append(candleTimeFrame.value())
        .append("_")
        .append(ticker.getExchangeSegment().value())
        .append("_")
        .append(ticker.getInstrumentType().value())
        .toString();
  }

  private void saveStockData(CandleTimeFrame candleTimeFrame, Set<StockData> stockDataList) {
    List<StockDataPersistable> stockDataPersitableList =
        stockDataList.stream()
            .map(p -> stockDataDailyMapper.mapDomainToPersistable((StockDataDaily) p))
            .collect(Collectors.toList());
    stockDataFactory.getStockDataRepository(candleTimeFrame).insert(stockDataPersitableList);
    //               .saveAll(stockDataPersitableList);
  }

  public Set<StockData> getStockDataForTimeFrame(
      String stockSymbol, CandleTimeFrame candleTimeFrame) {

    return stockPricesStore.get(stockSymbol).get(candleTimeFrame);
  }

  public Set<StockData> getStockDataForRange(
      String stockSymbol, CandleTimeFrame candleTimeFrame, Date fromDate, Date toDate) {
    // TO Implement
    return stockPricesStore.get(stockSymbol).get(candleTimeFrame);
  }

  public List<Bar> getBarSeries(String stockSymbol, CandleTimeFrame candleTimeFrame) {

    return barSeriesMap.get(stockSymbol).get(candleTimeFrame).getBarData();
  }

  public List<Bar> getBarSeriesForRange(
      String stockSymbol, CandleTimeFrame candleTimeFrame, Date fromDate, Date toDate) {
    // TO Implement
    return barSeriesMap.get(stockSymbol).get(candleTimeFrame).getBarData();
  }
}
