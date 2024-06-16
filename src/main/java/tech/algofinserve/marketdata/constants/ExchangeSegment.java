package tech.algofinserve.marketdata.constants;

public enum ExchangeSegment {
  NSE("NSE"),
  BSE("BSE"),
  NFO("NFO");

  private final String value;

  private ExchangeSegment(String value) {
    this.value = value;
  }

  public String value() {
    return this.value;
  }

  public ExchangeSegment fromValue(String value) {
    ExchangeSegment[] exchangeSegments = values();

    for (int i = 0; i < exchangeSegments.length; ++i) {
      ExchangeSegment c = exchangeSegments[i];
      if (c.value.equals(value)) {
        return c;
      }
    }

    throw new IllegalArgumentException(value);
  }
}
