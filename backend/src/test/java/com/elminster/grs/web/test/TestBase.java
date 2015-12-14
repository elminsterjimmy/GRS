package com.elminster.grs.web.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.elminster.web.grs.GSRBackendApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest({"server.port=0", "management.port=0"})
@SpringApplicationConfiguration(classes = GSRBackendApplication.class)
//@TestPropertySource(locations="classpath:test.properties")
public class TestBase {
  
  protected static final String LOCALHOST = "http://localhost:";
  
  protected static final Log logger = LogFactory.getLog(TestBase.class);

  @Value("${local.server.port}")
  protected int port;
}
