package com.elminster.grs.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.elminster.grs.web.helper.JsonResponseBuilder;
import com.elminster.grs.web.request.vo.LoginJsonModel;
import com.elminster.grs.web.request.vo.RegisterJsonModel;
import com.elminster.grs.web.response.JsonResponse;
import com.elminster.grs.web.service.AuthenticationService;
import com.elminster.grs.web.service.LoginException;
import com.elminster.grs.web.service.RegisterException;
import com.elminster.grs.web.service.ServiceException;
import com.elminster.spring.security.service.TokenAuthenticationService;
import com.elminster.web.commons.util.IpFinder;

/**
 * The authentication controller.
 * 
 * @author jgu
 * @version 1.0
 */
@RestController
public class AuthenticationController extends BaseController {

  public static final String LOGIN_URL = "/user/login";
  public static final String TEST_USERNAME_URL = "/user/usernameOccupied";
  public static final String REGISTER_URL = "/user/register";

  @Autowired
  private TokenAuthenticationService tokenAuthenticationService;
  @Autowired
  private AuthenticationService authenticationService;
  
  private static final JsonResponseBuilder jsonResponseBuilder = JsonResponseBuilder.INSTANCE;

  /**
   * User login returns http states 202 if success.
   */
  @RequestMapping(value = LOGIN_URL, method = RequestMethod.POST)
  public JsonResponse login(@Valid @RequestBody LoginJsonModel login,
      HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
    login.setIpComeFrom(IpFinder.getRequestIpAddress(request));
    JsonResponse jsonResponse = null;
    try {
      Authentication authentication = authenticationService.login(login);
      // add authentication token into response's header
      tokenAuthenticationService.addAuthentication2Header(response, authentication);
      response.setStatus(HttpServletResponse.SC_ACCEPTED);
      jsonResponse = jsonResponseBuilder.buildJsonResponse();
    } catch (LoginException e) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      jsonResponse = jsonResponseBuilder.buildErrorJsonResponse(e);
    }
    return jsonResponse;
  }

  @RequestMapping(value = TEST_USERNAME_URL, method = RequestMethod.POST)
  public @ResponseBody JsonResponse checkUsernameOccupied(@Valid @RequestBody String username) throws ServiceException {
    return jsonResponseBuilder.buildJsonResponse(authenticationService.isUsernameOccupied(username));
  }

  /**
   * Register user returns http states 202 if success.
   */
  @RequestMapping(value = REGISTER_URL, method = RequestMethod.POST)
  public JsonResponse register(@Valid @RequestBody RegisterJsonModel model, HttpServletRequest request,
      HttpServletResponse response) throws IOException, Exception {
    model.setIpComeFrom(IpFinder.getRequestIpAddress(request));
    JsonResponse jsonResponse = null;
    try {
      Authentication authentication = authenticationService.register(model);
      // add authentication token into response's header
      tokenAuthenticationService.addAuthentication2Header(response, authentication);
      response.setStatus(HttpServletResponse.SC_ACCEPTED);
      jsonResponse = jsonResponseBuilder.buildJsonResponse();
    } catch (RegisterException e) {
      response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
      jsonResponse = jsonResponseBuilder.buildErrorJsonResponse(e);
    }
    return jsonResponse;
  }
}
