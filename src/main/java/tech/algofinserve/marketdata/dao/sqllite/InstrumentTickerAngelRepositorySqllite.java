package tech.algofinserve.marketdata.dao.sqllite;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.algofinserve.marketdata.model.persistable.InstrumentTickerAngelPersistable;

@Repository
public interface InstrumentTickerAngelRepositorySqllite
    extends JpaRepository<InstrumentTickerAngelPersistable, Long> {

  //	@Query("from user_vehicle_detail u where u.user_id = :user_id" )
  //	List<UserVehicleDetail> findAllByUserId( @Param("user_id") Long userId);
  /*  	@Query("from instrument_ticker_angel u where u.exch_seg = :exchSeg" )
  List<InstrumentTickerAngelPersistable> findInstrumentTickerByExchangeSegment(
          @Param("exchSeg")String exchSeg);*/

  List<InstrumentTickerAngelPersistable> findAllByExchSeg(String exchSeg);
}
