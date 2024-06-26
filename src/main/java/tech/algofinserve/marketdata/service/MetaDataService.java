package tech.algofinserve.marketdata.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import tech.algofinserve.marketdata.constants.ExchangeSegment;
import tech.algofinserve.marketdata.model.domain.InstrumentTickerAngel;

public interface MetaDataService {

  public void readALlChartInkSymbolFile();

  Set<String> getAllChartInkSymbolList();

  public void loadInstrumentsTickerFromAPI();

  public void saveInstrumentTickerList(List<InstrumentTickerAngel> instrumentTickerAngelList);

  public void deleteAllInstrumentTicker();

  public Map<String, InstrumentTickerAngel> getInstrumentTickerMapForExchangeSegment(
      ExchangeSegment exchangeSegment);

  public InstrumentTickerAngel getInstrumentTickerForStockName(
      String stockName, ExchangeSegment exchangeSegment);
}
