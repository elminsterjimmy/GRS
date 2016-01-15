package com.elminster.grs.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan({
  "com.elminster.grs.crawler",
  "com.elminster.spring.security.service",
  "com.elminster.grs.giantbomb.service" })
@EnableScheduling
public class CrawlerApplication {

  /**
   * The main.
   * 
   * @param args
   *          the arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(CrawlerApplication.class, args);
  }
}
