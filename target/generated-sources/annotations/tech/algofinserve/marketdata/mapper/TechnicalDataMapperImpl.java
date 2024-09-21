package tech.algofinserve.marketdata.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tech.algofinserve.marketdata.model.domain.TechnicalData;
import tech.algofinserve.marketdata.model.persistable.TechnicalDataPersistable;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-21T18:26:15+0530",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 11.0.23 (Amazon.com Inc.)"
)
@Component
public class TechnicalDataMapperImpl implements TechnicalDataMapper {

    @Override
    public TechnicalDataPersistable mapDomainToPersistable(TechnicalData technicalData) {
        if ( technicalData == null ) {
            return null;
        }

        TechnicalDataPersistable technicalDataPersistable = new TechnicalDataPersistable();

        technicalDataPersistable.setSymbolId( technicalData.getSymbolId() );
        technicalDataPersistable.setTimestamp( technicalData.getTimestamp() );
        technicalDataPersistable.setEma8( technicalData.getEma8() );
        technicalDataPersistable.setEma13( technicalData.getEma13() );
        technicalDataPersistable.setEma21( technicalData.getEma21() );
        technicalDataPersistable.setEma34( technicalData.getEma34() );
        technicalDataPersistable.setEma55( technicalData.getEma55() );
        technicalDataPersistable.setEma89( technicalData.getEma89() );
        technicalDataPersistable.setEma200( technicalData.getEma200() );
        technicalDataPersistable.setRsi3( technicalData.getRsi3() );
        technicalDataPersistable.setRsi14( technicalData.getRsi14() );
        technicalDataPersistable.setRsi21( technicalData.getRsi21() );
        technicalDataPersistable.setHull21( technicalData.getHull21() );
        technicalDataPersistable.setHull55( technicalData.getHull55() );
        technicalDataPersistable.setHull89( technicalData.getHull89() );
        technicalDataPersistable.setVwap( technicalData.getVwap() );
        technicalDataPersistable.setVwma21( technicalData.getVwma21() );
        technicalDataPersistable.setRsi14ema9( technicalData.getRsi14ema9() );
        technicalDataPersistable.setRsi14ema21( technicalData.getRsi14ema21() );
        technicalDataPersistable.setIchimokuCloudTop( technicalData.getIchimokuCloudTop() );
        technicalDataPersistable.setIchimokuCloudBottom( technicalData.getIchimokuCloudBottom() );
        technicalDataPersistable.setIchimokuCloudBaseLine( technicalData.getIchimokuCloudBaseLine() );
        technicalDataPersistable.setIchimokuCloudConversionLine( technicalData.getIchimokuCloudConversionLine() );
        technicalDataPersistable.setMacd22107( technicalData.getMacd22107() );
        technicalDataPersistable.setMacdSignal22107( technicalData.getMacdSignal22107() );
        technicalDataPersistable.setMacdHist22107( technicalData.getMacdHist22107() );
        technicalDataPersistable.setMacd26129( technicalData.getMacd26129() );
        technicalDataPersistable.setMacdSignal26129( technicalData.getMacdSignal26129() );
        technicalDataPersistable.setMacdHist26129( technicalData.getMacdHist26129() );
        technicalDataPersistable.setMacd1153( technicalData.getMacd1153() );
        technicalDataPersistable.setMacdSignal1153( technicalData.getMacdSignal1153() );
        technicalDataPersistable.setMacdHist1153( technicalData.getMacdHist1153() );
        technicalDataPersistable.setAtr( technicalData.getAtr() );
        technicalDataPersistable.setDiPositive14( technicalData.getDiPositive14() );
        technicalDataPersistable.setDinegative14( technicalData.getDinegative14() );
        technicalDataPersistable.setAdx14( technicalData.getAdx14() );
        technicalDataPersistable.setSupertrend103( technicalData.getSupertrend103() );
        technicalDataPersistable.setSupertrend112( technicalData.getSupertrend112() );
        technicalDataPersistable.setBbUpper202( technicalData.getBbUpper202() );
        technicalDataPersistable.setBbLower202( technicalData.getBbLower202() );
        technicalDataPersistable.setBbMiddle202( technicalData.getBbMiddle202() );

        return technicalDataPersistable;
    }

    @Override
    public TechnicalData mapPersistableToDomain(TechnicalDataPersistable technicalDataPersistable) {
        if ( technicalDataPersistable == null ) {
            return null;
        }

        TechnicalData technicalData = new TechnicalData();

        technicalData.setSymbolId( technicalDataPersistable.getSymbolId() );
        technicalData.setTimestamp( technicalDataPersistable.getTimestamp() );
        technicalData.setEma8( technicalDataPersistable.getEma8() );
        technicalData.setEma13( technicalDataPersistable.getEma13() );
        technicalData.setEma21( technicalDataPersistable.getEma21() );
        technicalData.setEma34( technicalDataPersistable.getEma34() );
        technicalData.setEma55( technicalDataPersistable.getEma55() );
        technicalData.setEma89( technicalDataPersistable.getEma89() );
        technicalData.setEma200( technicalDataPersistable.getEma200() );
        technicalData.setRsi3( technicalDataPersistable.getRsi3() );
        technicalData.setRsi14( technicalDataPersistable.getRsi14() );
        technicalData.setRsi21( technicalDataPersistable.getRsi21() );
        technicalData.setHull21( technicalDataPersistable.getHull21() );
        technicalData.setHull55( technicalDataPersistable.getHull55() );
        technicalData.setHull89( technicalDataPersistable.getHull89() );
        technicalData.setVwap( technicalDataPersistable.getVwap() );
        technicalData.setVwma21( technicalDataPersistable.getVwma21() );
        technicalData.setRsi14ema9( technicalDataPersistable.getRsi14ema9() );
        technicalData.setRsi14ema21( technicalDataPersistable.getRsi14ema21() );
        technicalData.setIchimokuCloudTop( technicalDataPersistable.getIchimokuCloudTop() );
        technicalData.setIchimokuCloudBottom( technicalDataPersistable.getIchimokuCloudBottom() );
        technicalData.setIchimokuCloudBaseLine( technicalDataPersistable.getIchimokuCloudBaseLine() );
        technicalData.setIchimokuCloudConversionLine( technicalDataPersistable.getIchimokuCloudConversionLine() );
        technicalData.setMacd22107( technicalDataPersistable.getMacd22107() );
        technicalData.setMacdSignal22107( technicalDataPersistable.getMacdSignal22107() );
        technicalData.setMacdHist22107( technicalDataPersistable.getMacdHist22107() );
        technicalData.setMacd26129( technicalDataPersistable.getMacd26129() );
        technicalData.setMacdSignal26129( technicalDataPersistable.getMacdSignal26129() );
        technicalData.setMacdHist26129( technicalDataPersistable.getMacdHist26129() );
        technicalData.setMacd1153( technicalDataPersistable.getMacd1153() );
        technicalData.setMacdSignal1153( technicalDataPersistable.getMacdSignal1153() );
        technicalData.setMacdHist1153( technicalDataPersistable.getMacdHist1153() );
        technicalData.setAtr( technicalDataPersistable.getAtr() );
        technicalData.setDiPositive14( technicalDataPersistable.getDiPositive14() );
        technicalData.setDinegative14( technicalDataPersistable.getDinegative14() );
        technicalData.setAdx14( technicalDataPersistable.getAdx14() );
        technicalData.setSupertrend103( technicalDataPersistable.getSupertrend103() );
        technicalData.setSupertrend112( technicalDataPersistable.getSupertrend112() );
        technicalData.setBbUpper202( technicalDataPersistable.getBbUpper202() );
        technicalData.setBbLower202( technicalDataPersistable.getBbLower202() );
        technicalData.setBbMiddle202( technicalDataPersistable.getBbMiddle202() );

        return technicalData;
    }
}
