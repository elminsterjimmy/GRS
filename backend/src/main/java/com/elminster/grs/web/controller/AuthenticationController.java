package com.elminster.grs.web.controller;

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

import com.elminster.grs.web.helper.BindingResultHelper;
import com.elminster.grs.web.model.LoginDetails;
import com.elminster.grs.web.security.service.TokenAuthenticationService;

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

  @RequestMapping(value = "/user/login", method = RequestMethod.POST)
  public void login(@Valid @RequestBody LoginDetails login, HttpServletResponse response) {
    String username = login.getUsername();
    String password = login.getPassword();
    logger.debug("login with [" + username + ", " + password + "].");
    
    UserDetails details = userDetailsService.loadUserByUsername(username);
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(details.getUsername(),
        details.getPassword());
    authentication.setDetails(details);

    tokenAuthenticationService.addAuthentication2Header(response, authentication);
  }

  @ExceptionHandler
  @ResponseBody
  public ResponseEntity<String> handleException(MethodArgumentNotValidException exception) {
    return new ResponseEntity<>(BindingResultHelper.INSTANCE.buildErrorString(exception.getBindingResult()),
        HttpStatus.BAD_REQUEST);
  }
}
