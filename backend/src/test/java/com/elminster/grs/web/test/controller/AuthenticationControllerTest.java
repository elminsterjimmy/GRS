package com.elminster.grs.web.test.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.elminster.grs.web.GSRBackendApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GSRBackendApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class AuthenticationControllerTest {

  @Value("${local.server.port}")
  int port;

}