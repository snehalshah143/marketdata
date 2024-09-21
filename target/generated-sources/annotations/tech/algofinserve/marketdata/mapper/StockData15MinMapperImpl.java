package tech.algofinserve.marketdata.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tech.algofinserve.marketdata.model.domain.StockData15Min;
import tech.algofinserve.marketdata.model.persistable.StockData15MinPersistable;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-21T18:26:14+0530",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 11.0.23 (Amazon.com Inc.)"
)
@Component
public class StockData15MinMapperImpl implements StockData15MinMapper {

    @Override
    public StockData15MinPersistable mapDomainToPersistable(StockData15Min stockData15Min) {
        if ( stockData15Min == null ) {
            return null;
        }

        StockData15MinPersistable stockData15MinPersistable = new StockData15MinPersistable();

        stockData15MinPersistable.setCandleNum( stockData15Min.getCandleNum() );
        stockData15MinPersistable.setOpen( stockData15Min.getOpen() );
        stockData15MinPersistable.setHigh( stockData15Min.getHigh() );
        stockData15MinPersistable.setLow( stockData15Min.getLow() );
        stockData15MinPersistable.setClose( stockData15Min.getClose() );
        stockData15MinPersistable.setVolume( stockData15Min.getVolume() );
        stockData15MinPersistable.setOpenInterest( stockData15Min.getOpenInterest() );
        stockData15MinPersistable.setTechnicalData( stockData15Min.getTechnicalData() );
        stockData15MinPersistable.setSymbolId( stockData15Min.getSymbolId() );
        stockData15MinPersistable.setTimestamp( stockData15Min.getTimestamp() );

        return stockData15MinPersistable;
    }

    @Override
    public StockData15Min mapPersistableToDomain(StockData15MinPersistable stockData15MinPersistable) {
        if ( stockData15MinPersistable == null ) {
            return null;
        }

        StockData15Min stockData15Min = new StockData15Min();

        stockData15Min.setSymbolId( stockData15MinPersistable.getSymbolId() );
        stockData15Min.setTimestamp( stockData15MinPersistable.getTimestamp() );
        stockData15Min.setTechnicalData( stockData15MinPersistable.getTechnicalData() );
        stockData15Min.setCandleNum( stockData15MinPersistable.getCandleNum() );
        stockData15Min.setOpen( stockData15MinPersistable.getOpen() );
        stockData15Min.setHigh( stockData15MinPersistable.getHigh() );
        stockData15Min.setLow( stockData15MinPersistable.getLow() );
        stockData15Min.setClose( stockData15MinPersistable.getClose() );
        stockData15Min.setVolume( stockData15MinPersistable.getVolume() );
        stockData15Min.setOpenInterest( stockData15MinPersistable.getOpenInterest() );

        return stockData15Min;
    }
}
