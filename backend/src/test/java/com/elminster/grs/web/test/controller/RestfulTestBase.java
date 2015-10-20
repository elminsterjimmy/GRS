package com.elminster.grs.web.test.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest({"server.port=0", "management.port=0"})
public class RestfulTestBase {
  
  protected static final String LOCALHOST = "http://localhost:";
  
  protected static final Log logger = LogFactory.getLog(RestfulTestBase.class);

  protected RestTemplate restTemplate = new TestRestTemplate();
  
  @Value("${local.server.port}")
  protected int port;
}
