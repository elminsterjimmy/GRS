package com.elminster.grs.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

/**
 * The application.
 * 
 * @author jgu
 * @version 1.0
 */
@SpringBootApplication
@RestController
@ComponentScan({
  "com.elminster.spring.security",
  "com.elminster.grs.app",
  "com.elminster.grs.web",
  "com.elminster.grs.crawler",
  "com.elminster.spring.security.service",
  "com.elminster.grs.giantbomb.service" })
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
