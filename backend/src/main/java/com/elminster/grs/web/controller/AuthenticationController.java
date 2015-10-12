package com.elminster.grs.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.elminster.common.util.ExceptionUtil;
import com.elminster.grs.web.helper.BindingResultHelper;
import com.elminster.grs.web.helper.IpFinder;
import com.elminster.grs.web.model.LoginJsonModel;
import com.elminster.grs.web.model.RegisterJsonModel;
import com.elminster.spring.security.model.UserDetailsImpl;
import com.elminster.spring.security.service.TokenAuthenticationService;

/**
 * The authentication controller.
 * 
 * @author jgu
 * @version 1.0
 */
@RestController
public class AuthenticationController {

  private static final Log logger = LogFactory.getLog(AuthenticationController.class);

  @Autowired
  private TokenAuthenticationService tokenAuthenticationService;
  @Autowired
  private UserDetailsService userDetailsService;

  /**
   * Login.
   * 
   * @param login
   *          the login JSON
   * @param response
   *          the HTTP response
   * @throws IOException
   *           on error
   */
  @RequestMapping(value = "/user/login", method = RequestMethod.POST)
  public void login(@Valid @RequestBody LoginJsonModel login, HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String username = login.getUsername();
    String password = login.getPassword();
    long requestTs = login.getCurrentTs();
    logger.debug("login with [" + username + ", " + password + "] at " + new Date(requestTs) + ".");

    try {
      UserDetailsImpl details = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);

      if (null != details && details.getUsername().equals(username) && details.getPassword().equals(password)) {
        details.getUser().setLastLoginDate(new Date(System.currentTimeMillis()));
        details.getUser().setLastLoginIp(IpFinder.getRequestIpAddress(request));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            details.getUsername(), details.getPassword(), details.getAuthorities());
        authentication.setDetails(details);

        tokenAuthenticationService.addAuthentication2Header(response, authentication);
      } else {
        // login failed
        if (logger.isDebugEnabled()) {
          logger.debug("Login Failed. Incorrect username or password.");
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Login Failed. Incorrect username or password.");
      }
    } catch (Exception e) {
      if (logger.isDebugEnabled()) {
        logger.debug("Login Failed. Internet server error. Cause: " + ExceptionUtil.getFullStackTrace(e));
      }
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Login Failed.");
    }
  }

  @RequestMapping(value = "/user/register", method = RequestMethod.POST)
  public void register(@Valid @RequestBody RegisterJsonModel register, HttpServletResponse response) throws IOException {
    String username = register.getUsername();
    String password = register.getPassword();
    String email = register.getEmail();
    long requestTs = register.getCurrentTs();
    logger.debug("register with [" + username + ", " + password + "] at " + new Date(requestTs) + ".");

    UserDetails details = userDetailsService.loadUserByUsername(username);

    if (null != details && details.getUsername().equals(username) && details.getPassword().equals(password)) {
      // user already exists
    } else {
      
    }
  }

  // handle validate exception
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public ResponseEntity<String> handleException(MethodArgumentNotValidException exception) {
    if (logger.isDebugEnabled()) {
      logger.debug("Validate failed. Details: " + exception.getLocalizedMessage());
    }
    return new ResponseEntity<>(BindingResultHelper.INSTANCE.buildErrorString(exception.getBindingResult()),
        HttpStatus.BAD_REQUEST);
  }
}
