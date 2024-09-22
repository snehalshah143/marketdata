package tech.algofinserve.marketdata.model.persistable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import tech.algofinserve.marketdata.model.domain.TechnicalData;

// @Document(collection = "stock_data_daily")
@MappedSuperclass
public abstract class StockDataPersistable implements Serializable {
  /*    public StockDataPersistable(CandleTimeFrame candleTimeFrame) {
      super(candleTimeFrame);
  }*/
  public StockDataPersistable() {}

  /*  @EmbeddedId
  private StockDataId id;*/
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String symbolId;
  private LocalDateTime timestamp;
  private Long candleNum;

  private Double open;

  private Double high;

  private Double low;

  private Double close;

  private Long volume;

  private Long openInterest;

  private TechnicalData technicalData;

  public Long getCandleNum() {
    return candleNum;
  }

  public void setCandleNum(Long candleNum) {
    this.candleNum = candleNum;
  }

  public Double getOpen() {
    return open;
  }

  public void setOpen(Double open) {
    this.open = open;
  }

  public Double getHigh() {
    return high;
  }

  public void setHigh(Double high) {
    this.high = high;
  }

  public Double getLow() {
    return low;
  }

  public void setLow(Double low) {
    this.low = low;
  }

  public Double getClose() {
    return close;
  }

  public void setClose(Double close) {
    this.close = close;
  }

  public Long getVolume() {
    return volume;
  }

  public void setVolume(Long volume) {
    this.volume = volume;
  }

  public Long getOpenInterest() {
    return openInterest;
  }

  public void setOpenInterest(Long openInterest) {
    this.openInterest = openInterest;
  }

  public TechnicalData getTechnicalData() {
    return technicalData;
  }

  public void setTechnicalData(TechnicalData technicalData) {
    this.technicalData = technicalData;
  }

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
    StockDataPersistable that = (StockDataPersistable) o;
    return // id.equals(that.id)
    symbolId.equals(that.symbolId)
        && timestamp.equals(that.timestamp)
        && Objects.equals(candleNum, that.candleNum)
        && Objects.equals(open, that.open)
        && Objects.equals(high, that.high)
        && Objects.equals(low, that.low)
        && Objects.equals(close, that.close)
        && Objects.equals(volume, that.volume)
        && Objects.equals(openInterest, that.openInterest)
        && Objects.equals(technicalData, that.technicalData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        // id,
        symbolId,
        timestamp,
        candleNum,
        open,
        high,
        low,
        close,
        volume,
        openInterest,
        technicalData);
  }
}
