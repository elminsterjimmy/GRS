package com.elminster.grs.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * The application.
 * 
 * @author jgu
 * @version 1.0
 */
@SpringBootApplication
@RestController
@ComponentScan({ "com.elminster.spring.security", "com.elminster.grs" })
@EnableJpaRepositories({ "com.elminster.spring.security", "com.elminster.grs" })
@EntityScan({ "com.elminster.spring.security", "com.elminster.grs" })
@EnableTransactionManagement
@EnableSpringDataWebSupport
public class GSRBackendApplication {

  /**
   * The main.
   * 
   * @param args
   *          the arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(GSRBackendApplication.class, args);
  }
}
