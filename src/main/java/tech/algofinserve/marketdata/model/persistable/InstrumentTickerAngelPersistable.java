package tech.algofinserve.marketdata.model.persistable;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// @Document(collection = "instrument_ticker_angel")
@Entity
@Table(name = "instrument_ticker_angel")
public class InstrumentTickerAngelPersistable implements Serializable {
  @Id private String token;

  private String symbol;

  private String name;

  private String expiry;

  private String strike;

  private String lotsize;

  private String instrumenttype;

  private String exchSeg;

  private String tickSize;

  public String getExpiry() {
    return expiry;
  }

  public void setExpiry(String expiry) {
    this.expiry = expiry;
  }

  public String getStrike() {
    return strike;
  }

  public void setStrike(String strike) {
    this.strike = strike;
  }

  public String getLotsize() {
    return lotsize;
  }

  public void setLotsize(String lotsize) {
    this.lotsize = lotsize;
  }

  public String getInstrumenttype() {
    return instrumenttype;
  }

  public void setInstrumenttype(String instrumenttype) {
    this.instrumenttype = instrumenttype;
  }

  public String getExchSeg() {
    return exchSeg;
  }

  public void setExchSeg(String exchSeg) {
    this.exchSeg = exchSeg;
  }

  public String getTickSize() {
    return tickSize;
  }

  public void setTickSize(String tickSize) {
    this.tickSize = tickSize;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
