package com.elminster.grs.frontend.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * The application.
 * 
 * @author jgu
 * @version 1.0
 */
@SpringBootApplication
@RestController
public class GRSFrontendApplication {

  /**
   * The main.
   * 
   * @param args
   *          the arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(GRSFrontendApplication.class, args);
  }
}
