package com.elminster.grs.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.elminster.grs.web.constants.Authorities;
import com.elminster.grs.web.request.vo.LoginJsonModel;
import com.elminster.grs.web.request.vo.RegisterJsonModel;
import com.elminster.grs.web.response.JsonResponseTemplate;
import com.elminster.grs.web.response.vo.BasicUserInfo;
import com.elminster.grs.web.service.AuthenticationService;
import com.elminster.grs.web.service.LoginException;
import com.elminster.grs.web.service.RegisterException;
import com.elminster.grs.web.service.ServiceErrorCode;
import com.elminster.grs.web.service.UserService;
import com.elminster.grs.web.service.UserServiceException;
import com.elminster.spring.security.domain.User;
import com.elminster.spring.security.model.UserDetailsImpl;
import com.elminster.spring.security.service.TokenAuthenticationService;
import com.elminster.web.commons.response.JsonResponse;
import com.elminster.web.commons.util.IpFinder;

/**
 * The user controller.
 * 
 * @author jgu
 * @version 1.0
 */
@RestController
@RequestMapping("/v1.0/user")
public class UserController extends BaseController {

  @Autowired
  private TokenAuthenticationService tokenAuthenticationService;
  @Autowired
  private AuthenticationService authenticationService;
  @Autowired
  private UserService userService;

  @RequestMapping(value = "/test", method = RequestMethod.GET)
  public @ResponseBody JsonResponse test(HttpServletResponse response) {
    response.setStatus(HttpServletResponse.SC_ACCEPTED);
    return new JsonResponse().setData("ping OK");
  }

  /**
   * Get all users' information.
   */
  @RequestMapping(method = RequestMethod.GET)
  public @ResponseBody JsonResponse getAllUsers(HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserDetailsImpl ud = (UserDetailsImpl) auth.getDetails();
    // the user
    User user = ud.getUser();
    if (user.getAuthorities().contains(Authorities.ADMIN)) {

    }
    return new JsonResponse().setData("auth OK");
  }

  /**
   * Get current user's information.
   */
  @RequestMapping(value = "/current", method = RequestMethod.GET)
  public @ResponseBody JsonResponse getCurrentUser() throws IOException, Exception {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserDetailsImpl ud = (UserDetailsImpl) auth.getDetails();
    User currentUser = ud.getUser();
    final int userId = currentUser.getId();
    JsonResponse jsonResponse = new JsonResponseTemplate() {
      protected Object callback() throws Exception {
        return userService.getBasicUserInfo(userId);
      }
    }.getJsonResponse();
    return jsonResponse;
  }

  @RequestMapping(value = "/current", method = RequestMethod.PUT)
  public @ResponseBody JsonResponse updateCurrentUser() {
    // TODO
    return null;
  }

  @RequestMapping(value = "/current", method = RequestMethod.DELETE)
  public @ResponseBody JsonResponse deleteCurrentUser() {
    // TODO
    return null;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public @ResponseBody JsonResponse getUser() {
    // TODO
    return null;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public @ResponseBody JsonResponse updateUser() {
    // TODO
    return null;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public @ResponseBody JsonResponse deleteUser() {
    // TODO
    return null;
  }

  /**
   * User login returns http states 202 if success.
   */
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public JsonResponse login(@Valid @RequestBody LoginJsonModel login, HttpServletRequest request,
      HttpServletResponse response) throws IOException, Exception {
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

  /**
   * Register user returns http states 201 if success created.
   */
  @RequestMapping(method = RequestMethod.PUT)
  public JsonResponse register(@Valid @RequestBody RegisterJsonModel model, HttpServletRequest request,
      HttpServletResponse response) throws IOException, Exception {
    model.setIpComeFrom(IpFinder.getRequestIpAddress(request));
    JsonResponse jsonResponse = null;
    try {
      Authentication authentication = authenticationService.register(model);
      // add authentication token into response's header
      tokenAuthenticationService.addAuthentication2Header(response, authentication);
      response.setStatus(HttpServletResponse.SC_CREATED);
      jsonResponse = jsonResponseBuilder.buildJsonResponse();
    } catch (RegisterException e) {
      response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
      jsonResponse = jsonResponseBuilder.buildErrorJsonResponse(e);
    }
    return jsonResponse;
  }

  /**
   * Check the username is occupied or not.
   */
  @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
  public JsonResponse checkUsernameOccupied(@PathVariable String username) throws IOException, Exception {
    boolean occupied = authenticationService.isUsernameOccupied(username);
    JsonResponse jsonResponse = jsonResponseBuilder.buildJsonResponse();
    jsonResponse.setData(occupied);
    if (occupied) {
      jsonResponse.setStatus(JsonResponse.STATUS_ERROR);
      jsonResponseBuilder.generateResponseError(ServiceErrorCode.REGISTER_USERNAME_OCCUPIED.getCode(),
          "Username already exist.");
    }
    return jsonResponse;
  }
}
