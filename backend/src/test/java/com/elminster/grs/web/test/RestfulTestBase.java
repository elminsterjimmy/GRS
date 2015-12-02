package com.elminster.grs.web.test;

import org.springframework.boot.test.TestRestTemplate;
import org.springframework.web.client.RestTemplate;

public class RestfulTestBase extends TestBase {

  protected RestTemplate restTemplate = new TestRestTemplate();
}
