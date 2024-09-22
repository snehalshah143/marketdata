package tech.algofinserve.marketdata.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class StockDataId implements Serializable {
  private String symbolId;
  private LocalDateTime timestamp;

  public String getSymbolId() {
    return symbolId;
  }

  public void setSymbolId(String symbolId) {
    this.symbolId = symbolId;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StockDataId that = (StockDataId) o;
    return Objects.equals(symbolId, that.symbolId) && Objects.equals(timestamp, that.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(symbolId, timestamp);
  }

  @Override
  public String toString() {
    return "StockDataId{" + "symbolId='" + symbolId + '\'' + ", timestamp=" + timestamp + '}';
  }
}
