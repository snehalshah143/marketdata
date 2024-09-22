package tech.algofinserve.marketdata.util;

import tech.algofinserve.marketdata.constants.CandleTimeFrame;
import tech.algofinserve.marketdata.constants.ExchSeg;
import tech.algofinserve.marketdata.constants.InstrumentType;

public class SymbolKeyUtil {
  public static final String HYPEN = "-";

  public static String getSymbolId(
      String symbol,
      CandleTimeFrame candleTimeFrame,
      InstrumentType instrumentType,
      ExchSeg exchSeg) {
    // RELIANCE-D-EQ-NSE
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(symbol);
    stringBuilder.append(HYPEN);
    stringBuilder.append(candleTimeFrame.value());
    stringBuilder.append(HYPEN);
    stringBuilder.append(instrumentType.value());
    stringBuilder.append(HYPEN);
    stringBuilder.append(exchSeg.value());
    return stringBuilder.toString();
  }
}
