package tech.algofinserve.marketdata.service;

import static com.opencsv.CSVWriter.DEFAULT_SEPARATOR;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.algofinserve.marketdata.constants.ExchangeSegment;
import tech.algofinserve.marketdata.dao.InstrumentTickerAngelRepository;
import tech.algofinserve.marketdata.mapper.InstrumentTickerAngelMapper;
import tech.algofinserve.marketdata.model.domain.InstrumentTickerAngel;
import tech.algofinserve.marketdata.model.persistable.InstrumentTickerAngelPersistable;

@Service
public class AngelMetaDataServiceImpl implements MetaDataService {
  private final String ANGEL_ALL_INSTRUMENT_LIST_API =
      "https://margincalculator.angelbroking.com/OpenAPI_File/files/OpenAPIScripMaster.json";
  private final String ANGEL_ALL_INSTRUMENT_FILE_CSV = "src/main/resources/all_instrument.csv";
  private final String ANGEL_ALL_INSTRUMENT_FILE_TXT = "src/main/resources/all_instrument_txt.txt";

  @Autowired InstrumentTickerAngelRepository instrumentTickerRepository;

  @Autowired InstrumentTickerAngelMapper instrumentTickerMapper;

  // StockSymbol=StockName
  private Map<String, String> allChartInkSymbolMetaData = new HashMap<>();
  private Map<ExchangeSegment, Map<String, InstrumentTickerAngel>> instrumentTickerForExchangeMap =
      new ConcurrentHashMap<>();

  // Should Call On Server startup or load from cache
  public void readALlChartInkSymbolFile() {
    allChartInkSymbolMetaData.clear();
    InputStreamReader inputStreamReader;
    inputStreamReader =
        new InputStreamReader(getClass().getResourceAsStream("/chartink_symbol_list.csv"));
    String line = "";
    String splitBy = ",";

    BufferedReader br = new BufferedReader(inputStreamReader);
    while (true) // returns a Boolean value
    {
      try {
        if (!((line = br.readLine()) != null)) break;
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      line = line.replaceAll("\"", "");
      String[] stockLine = line.split(splitBy); // use comma as separator

      allChartInkSymbolMetaData.put(stockLine[2], stockLine[1]);
    }
  }

  public Set<String> getAllChartInkSymbolList() {
    if (allChartInkSymbolMetaData.isEmpty()) {
      readALlChartInkSymbolFile();
    }
    return allChartInkSymbolMetaData.keySet();
  }

  public void loadInstrumentsTickerFromAPI() {
    deleteAllInstrumentTicker();

    InputStreamReader inputStreamReader;

    //   inputStreamReader= readInstrumentTickerFileFromAngelServer();

    inputStreamReader =
        new InputStreamReader(getClass().getResourceAsStream("/OpenAPIScripMaster.json"));

    // map to GSON objects
    JsonElement root = new JsonParser().parse(inputStreamReader);
    ObjectMapper objectMapper = new ObjectMapper();
    JsonArray items = root.getAsJsonArray();

    items.forEach(
        item -> {
          try {

            InstrumentTickerAngel instrument =
                objectMapper.readValue(item.toString(), InstrumentTickerAngel.class);

            ExchangeSegment instrumentExchangeSegment = null;
            try {
              instrumentExchangeSegment = ExchangeSegment.valueOf(instrument.getExchSeg());
              populateInstrumentTickerForExchangeMap(instrument, instrumentExchangeSegment);

            } catch (IllegalArgumentException e) {

              // Do Nothing Ignored
            }

          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        });

    Arrays.stream(ExchangeSegment.values())
        .forEach(
            p -> {
              List<InstrumentTickerAngel> instrumentList = new ArrayList<>();
              instrumentList.addAll(instrumentTickerForExchangeMap.get(p).values());
              saveInstrumentTickerList(instrumentList);
            });

    // writeDataToFile(ANGEL_ALL_INSTRUMENT_FILE_CSV,InstrumentTickerAngel.class,
    // instrumentTickerAngelList);
  }

  private void populateInstrumentTickerForExchangeMap(
      InstrumentTickerAngel instrument, ExchangeSegment instrumentExchangeSegment) {
    if (instrumentTickerForExchangeMap.containsKey(instrumentExchangeSegment)) {
      instrumentTickerForExchangeMap
          .get(instrumentExchangeSegment)
          .put(instrument.getName(), instrument);
    } else {
      Map<String, InstrumentTickerAngel> instrumentTickerMap = new HashMap<>();
      instrumentTickerMap.put(instrument.getName(), instrument);
      instrumentTickerForExchangeMap.put(instrumentExchangeSegment, instrumentTickerMap);
    }
  }

  private InputStreamReader readInstrumentTickerFileFromAngelServer() {
    InputStreamReader inputStreamReader;
    URLConnection request = null;

    try {
      request = new URL(ANGEL_ALL_INSTRUMENT_LIST_API).openConnection();
      request.connect();
      inputStreamReader = new InputStreamReader((InputStream) request.getContent());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return inputStreamReader;
  }

  public void saveInstrumentTickerList(List<InstrumentTickerAngel> instrumentTickerAngelList) {
    List<InstrumentTickerAngelPersistable> instrumentPersistableList =
        instrumentTickerAngelList.stream()
            .map(p -> instrumentTickerMapper.mapDomainToPersistable(p))
            .collect(Collectors.toList());
    instrumentTickerRepository.saveAll(instrumentPersistableList);
  }

  public void deleteAllInstrumentTicker() {

    instrumentTickerRepository.deleteAll();
    instrumentTickerForExchangeMap.clear();
  }

  public Map<String, InstrumentTickerAngel> getInstrumentTickerMapForExchangeSegment(
      ExchangeSegment exchangeSegment) {
    if (!instrumentTickerForExchangeMap.containsKey(exchangeSegment)) {
      Map<String, InstrumentTickerAngel> instrumentTickerMap = new ConcurrentHashMap<>();

      List<InstrumentTickerAngelPersistable> instrumentTickerAngelList =
          instrumentTickerRepository.findInstrumentTickerByExchangeSegment(exchangeSegment.value());

      instrumentTickerAngelList.stream()
          .forEach(
              p -> {
                instrumentTickerMap.put(
                    p.getName(), instrumentTickerMapper.mapPersistableToDomain(p));
              });

      instrumentTickerForExchangeMap.put(exchangeSegment, instrumentTickerMap);
    }
    return instrumentTickerForExchangeMap.get(exchangeSegment);
  }

  public InstrumentTickerAngel getInstrumentTickerForStockName(
      String stockName, ExchangeSegment exchangeSegment) {

    return getInstrumentTickerMapForExchangeSegment(exchangeSegment).get(stockName);
  }

  public void writeDataToFile(String fileLocation, Class typeOfObject, List dataList) {
    Writer writer;
    try {
      writer = Files.newBufferedWriter(Paths.get(fileLocation));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
    mappingStrategy.setType(typeOfObject);

    StatefulBeanToCsv beanToCsv =
        new StatefulBeanToCsvBuilder(writer)
            .withMappingStrategy(mappingStrategy)
            .withSeparator(DEFAULT_SEPARATOR)
            .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
            .build();
    try {
      beanToCsv.write(dataList);
      System.out.println("File Writing completed.");
    } catch (CsvDataTypeMismatchException e) {
      throw new RuntimeException(e);
    } catch (CsvRequiredFieldEmptyException e) {
      throw new RuntimeException(e);
    }
  }
}
