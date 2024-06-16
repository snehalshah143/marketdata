package tech.algofinserve.marketdata.model.domain;

import java.io.Serializable;
import java.util.Objects;
import tech.algofinserve.marketdata.constants.ExchangeSegment;
import tech.algofinserve.marketdata.constants.InstrumentType;

public class Ticker implements Serializable {
  String token;
  String stockSymbol;
  InstrumentType instrumentType;
  ExchangeSegment exchangeSegment;

  String stockSymbolExch;
  String sector;
  String categorization;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getStockSymbol() {
    return stockSymbol;
  }

  public void setStockSymbol(String stockSymbol) {
    this.stockSymbol = stockSymbol;
  }

  public InstrumentType getInstrumentType() {
    return instrumentType;
  }

  public void setInstrumentType(InstrumentType instrumentType) {
    this.instrumentType = instrumentType;
  }

  public ExchangeSegment getExchangeSegment() {
    return exchangeSegment;
  }

  public void setExchangeSegment(ExchangeSegment exchangeSegment) {
    this.exchangeSegment = exchangeSegment;
  }

  public String getSector() {
    return sector;
  }

  public void setSector(String sector) {
    this.sector = sector;
  }

  public String getCategorization() {
    return categorization;
  }

  public void setCategorization(String categorization) {
    this.categorization = categorization;
  }

  public String getStockSymbolExch() {
    return stockSymbolExch;
  }

  public void setStockSymbolExch(String stockSymbolExch) {
    this.stockSymbolExch = stockSymbolExch;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Ticker ticker = (Ticker) o;
    return token.equals(ticker.token)
        && stockSymbol.equals(ticker.stockSymbol)
        && instrumentType == ticker.instrumentType
        && exchangeSegment == ticker.exchangeSegment
        && Objects.equals(sector, ticker.sector)
        && Objects.equals(categorization, ticker.categorization);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        token, stockSymbol, instrumentType, exchangeSegment, sector, categorization);
  }
}
