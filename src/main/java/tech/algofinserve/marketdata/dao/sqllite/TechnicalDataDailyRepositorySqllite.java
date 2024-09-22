package tech.algofinserve.marketdata.dao.sqllite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.algofinserve.marketdata.model.persistable.TechnicalDataPersistable;

@Repository
public interface TechnicalDataDailyRepositorySqllite
    extends JpaRepository<TechnicalDataPersistable, String> {}
