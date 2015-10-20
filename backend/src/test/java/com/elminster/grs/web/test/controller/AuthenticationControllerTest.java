package com.elminster.grs.web.test.controller;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.elminster.grs.web.GSRBackendApplication;
import com.elminster.grs.web.controller.AuthenticationController;
import com.elminster.grs.web.request.vo.LoginJsonModel;
import com.elminster.spring.security.filter.CsrfTokenFilter;

@SpringApplicationConfiguration(classes = GSRBackendApplication.class)
public class AuthenticationControllerTest extends RestfulTestBase {

  @Test
  public void testLogin() {
    LoginJsonModel login = new LoginJsonModel();
    login.setUsername("test").setPassword("test");
    // set header
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    String CSRF_TOKEN = "token";
    headers.set(CsrfTokenFilter.X_CSRF_TOKEN, CSRF_TOKEN);
//    headers.set("_", String.valueOf(System.currentTimeMillis()));
    // set cookie
    headers.add(HttpHeaders.COOKIE, CsrfTokenFilter.CSRF_TOKEN + "=" + CSRF_TOKEN);
    HttpEntity<LoginJsonModel> requestEntity = new HttpEntity<LoginJsonModel>(login, headers);
    String url = LOCALHOST + port + AuthenticationController.LOGIN_URL;
    logger.info("call " + url + " with " + login.toString());
    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    Assert.assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
  }
}
