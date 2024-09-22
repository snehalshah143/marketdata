package tech.algofinserve.marketdata.model.persistable;

// import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Table;

// @Document(collection = "stock_data_daily")
@Entity
@Table(
    name = "technical_data_daily"
    // , uniqueConstraints = {@UniqueConstraint(columnNames = {"symbolId", "timestamp"})}
    )
public class TechnicalDataDailyPersistable extends TechnicalDataPersistable {
  public TechnicalDataDailyPersistable() {
    //      super(CandleTimeFrame.ONE_DAY);
  }
}
