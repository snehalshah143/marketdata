package tech.algofinserve.marketdata.controller;

import com.angelbroking.smartapi.SmartConnect;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.ta4j.core.Bar;
import tech.algofinserve.infra.AngelApiKey;
import tech.algofinserve.infra.AngelBrokerConnector;
import tech.algofinserve.marketdata.constants.CandleTimeFrame;
import tech.algofinserve.marketdata.constants.ExchSeg;
import tech.algofinserve.marketdata.constants.InstrumentType;
import tech.algofinserve.marketdata.model.domain.StockData;
import tech.algofinserve.marketdata.model.domain.Ticker;
import tech.algofinserve.marketdata.service.AngelMarketDataServiceImpl;
import tech.algofinserve.marketdata.service.MetaDataService;

@RestController
public class MarketDataController {

  @Autowired AngelMarketDataServiceImpl angelMarketDataServiceImpl;

  @Autowired MetaDataService metaDataService;
  SmartConnect smartConnect = AngelBrokerConnector.getSmartConnectSession(new AngelApiKey());

  @PostMapping(path = "/marketdata/angel/downloadmarketdata")
  public ResponseEntity<String> downloadMarketData() {

    Ticker ticker = new Ticker();
    ticker.setToken(
        metaDataService.getInstrumentTickerForStockName("RELIANCE", ExchSeg.NSE).getToken());
    ticker.setStockSymbol("RELIANCE");
    ticker.setExchSeg(ExchSeg.NSE);
    ticker.setInstrumentType(InstrumentType.EQ);
    String fromDate = "2023-01-01 00:00";

    String toDate = "2024-09-17 00:00";

    Set<StockData> stockDataList =
        angelMarketDataServiceImpl.getHistoricalDataForTicker(
            smartConnect, ticker, CandleTimeFrame.ONE_DAY, fromDate, toDate);

    return new ResponseEntity<String>("Data Download Completed For Symbol.", HttpStatus.OK);
  }

  @PostMapping(
      path = "/marketdata/angel/downloadmarketdataforticker",
      consumes = "application/json")
  public ResponseEntity<String> downloadMarketDataForTicker(@RequestBody Ticker ticker) {

    //  Ticker ticker = new Ticker();
    ticker.setToken(
        metaDataService
            .getInstrumentTickerForStockName(ticker.getStockSymbol(), ticker.getExchSeg())
            .getToken());
    ticker.setStockSymbol(ticker.getStockSymbol());
    ticker.setExchSeg(ticker.getExchSeg());
    ticker.setInstrumentType(ticker.getInstrumentType());
    String fromDate = "2023-01-01 00:00";

    String toDate = "2024-09-17 00:00";

    Set<StockData> stockDataList =
        angelMarketDataServiceImpl.getHistoricalDataForTicker(
            smartConnect, ticker, CandleTimeFrame.ONE_DAY, fromDate, toDate);

    return new ResponseEntity<String>("Data Download Completed For Symbol.", HttpStatus.OK);
  }

  @PostMapping(path = "/marketdata/angel/getmarketdata")
  public ResponseEntity<List<StockData>> getStockData() {

    Ticker ticker = new Ticker();
    ticker.setToken(
        metaDataService.getInstrumentTickerForStockName("RELIANCE", ExchSeg.NSE).getToken());
    ticker.setStockSymbol("RELIANCE");
    ticker.setExchSeg(ExchSeg.NSE);

    String fromDate = "2023-01-01 00:00";

    String toDate = "2024-01-17 00:00";

    String timeStamp = "2023-01-04T00:00:00+05:30";

    //  Set<StockData> stockDataList=
    // angelMarketDataServiceImpl.getStockDataForTimeFrame(ticker.getStockSymbol(),
    // CandleTimeFrame.ONE_DAY);
    List<StockData> stockDataList =
        angelMarketDataServiceImpl.getStockDataForTicker(
            smartConnect, ticker, CandleTimeFrame.ONE_DAY, timeStamp);

    return new ResponseEntity<List<StockData>>(stockDataList, HttpStatus.OK);
  }

  @PostMapping(path = "/marketdata/angel/getbarseries")
  public ResponseEntity<List<Bar>> getBarSeriesData() {

    Ticker ticker = new Ticker();
    ticker.setToken(
        metaDataService.getInstrumentTickerForStockName("RELIANCE", ExchSeg.NSE).getToken());
    ticker.setStockSymbol("RELIANCE");
    ticker.setExchSeg(ExchSeg.NSE);
    ticker.setInstrumentType(InstrumentType.EQ);
    String fromDate = "2023-01-01 00:00";

    String toDate = "2024-01-17 00:00";

    List<Bar> barList =
        angelMarketDataServiceImpl.getBarSeries(ticker.getStockSymbol(), CandleTimeFrame.ONE_DAY);

    return new ResponseEntity<List<Bar>>(barList, HttpStatus.OK);
  }
}
