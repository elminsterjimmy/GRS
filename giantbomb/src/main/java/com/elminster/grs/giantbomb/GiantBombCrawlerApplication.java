package com.elminster.grs.giantbomb;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan({ "com.elminster.grs.giantbomb" })
@EnableJpaRepositories({ "com.elminster.grs.giantbomb" })
@EntityScan({ "com.elminster.grs.giantbomb" })
@EnableTransactionManagement
@EnableScheduling
public class GiantBombCrawlerApplication {

  /**
   * The main.
   * 
   * @param args
   *          the arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(GiantBombCrawlerApplication.class, args);
  }
  
  @Bean
  @ConfigurationProperties("spring.datasource")
  public DataSource dataSource() {
      return DataSourceBuilder.create().build();
  }
}
