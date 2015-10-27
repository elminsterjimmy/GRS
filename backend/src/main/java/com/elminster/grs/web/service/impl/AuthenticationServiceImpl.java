package com.elminster.grs.web.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.elminster.common.util.ExceptionUtil;
import com.elminster.grs.web.request.vo.LoginJsonModel;
import com.elminster.grs.web.request.vo.RegisterJsonModel;
import com.elminster.grs.web.service.AuthenticationService;
import com.elminster.grs.web.service.LoginException;
import com.elminster.grs.web.service.RegisterException;
import com.elminster.grs.web.service.ServiceErrorCode;
import com.elminster.grs.web.service.ServiceException;
import com.elminster.spring.security.domain.User;
import com.elminster.spring.security.model.UserDetailsImpl;
import com.elminster.spring.security.service.TokenAuthenticationService;
import com.elminster.spring.security.service.AuthUserService;

@Transactional
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
  
  private static final Log logger = LogFactory.getLog(AuthenticationServiceImpl.class);
  
  @Autowired
  private TokenAuthenticationService tokenAuthenticationService;
  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private AuthUserService userService;

  @Override
  public Authentication login(LoginJsonModel login) throws ServiceException {
    String username = login.getUsername();
    String password = login.getPassword();
    long requestTs = login.getCurrentTs();
    logger.debug("login with [" + username + ", " + password + "] at " + new Date(requestTs) + ".");

    try {
      UserDetailsImpl details = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);

      if (null != details && details.getUsername().equals(username) && details.getPassword().equals(password)) {
        if (details.isEnabled()) {
          details.getUser().setLastLoginDate(new Date(requestTs));
          details.getUser().setLastLoginIp(login.getIpComeFrom());
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
              details.getUsername(), details.getPassword(), details.getAuthorities());
          authentication.setDetails(details);
          return authentication;
        } else {
          // login failed: account is disabled
          String message = "Login Failed. Account is disabled.";
          if (logger.isDebugEnabled()) {
            logger.debug(message);
          }
          throw new LoginException(ServiceErrorCode.LOGIN_ACCOUNT_DISABLED, message);
        }
      } else {
        // login failed: incorrect username or password
        String message = "Login Failed. Incorrect username or password.";
        if (logger.isDebugEnabled()) {
          logger.debug(message);
        }
        throw new LoginException(ServiceErrorCode.LOGIN_INCORRECT_USERNAME_OR_PASSWORD, message);
      }
    } catch (Exception e) {
      if (e instanceof LoginException) {
        throw e;
      }
      String message = "Login Failed. Internet server error. Cause: " + ExceptionUtil.getFullStackTrace(e);
      if (logger.isDebugEnabled()) {
        logger.debug(message);
      }
      throw new ServiceException(ServiceErrorCode.UNEXCEPTED_EXCEPTION, message, e);
    }
  }

  @Override
  public boolean isUsernameOccupied(String username) throws ServiceException {
    return userService.isUsernameExists(username);
  }

  @Override
  public Authentication register(RegisterJsonModel model) throws ServiceException {
    String username = model.getUsername();
    String password = model.getPassword();
    String email = model.getEmail();
    long requestTs = model.getCurrentTs();
    
    try {
      if (!isUsernameOccupied(username)) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setLastLoginDate(new Date(requestTs));
        user.setLastLoginIp(model.getIpComeFrom());
        userService.addUser(user);
        UserDetails details = new UserDetailsImpl(user);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            details.getUsername(), details.getPassword(), details.getAuthorities());
        authentication.setDetails(details);
        return authentication;
      } else {
        // register failed: username occupied
        String message = "Register Failed. Username already exist.";
        if (logger.isDebugEnabled()) {
          logger.debug(message);
        }
        throw new RegisterException(ServiceErrorCode.REGISTER_USERNAME_OCCUPIED, message);
      }
    } catch (Exception e) {
      if (e instanceof RegisterException) {
        throw e;
      }
      String message = "Register Failed. Internet server error. Cause: " + ExceptionUtil.getFullStackTrace(e);
      if (logger.isDebugEnabled()) {
        logger.debug(message);
      }
      throw new ServiceException(ServiceErrorCode.UNEXCEPTED_EXCEPTION, message, e);
    }
  }

}
