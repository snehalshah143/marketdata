package tech.algofinserve.marketdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// (exclude = {DataSourceAutoConfiguration.class})
// @EnableJpaRepositories(basePackages = "tech.algofinserve.marketdata.dao.sqllite")
public class MarketDataApplication {

  public static void main(String[] args) {
    SpringApplication.run(MarketDataApplication.class, args);
  }
}
