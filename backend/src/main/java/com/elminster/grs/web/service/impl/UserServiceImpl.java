package com.elminster.grs.web.service.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.elminster.common.util.ExceptionUtil;
import com.elminster.grs.web.dao.UserExDao;
import com.elminster.grs.web.domain.UserEx;
import com.elminster.grs.web.request.vo.LoginJsonModel;
import com.elminster.grs.web.request.vo.RegisterJsonModel;
import com.elminster.grs.web.response.vo.BasicUserInfo;
import com.elminster.grs.web.service.LoginException;
import com.elminster.grs.web.service.RegisterException;
import com.elminster.grs.web.service.ServiceErrorCode;
import com.elminster.grs.web.service.ServiceException;
import com.elminster.grs.web.service.UserServiceException;
import com.elminster.grs.web.service.UserService;
import com.elminster.spring.security.domain.User;
import com.elminster.spring.security.model.UserDetailsImpl;
import com.elminster.spring.security.service.AuthUserService;
import com.elminster.spring.security.service.TokenAuthenticationService;

/**
 * The user service implememntaion.
 * 
 * @author jgu
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

  private static final Log logger = LogFactory.getLog(UserServiceImpl.class);
  
  @Autowired
  private TokenAuthenticationService tokenAuthenticationService;
  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private AuthUserService authUserService;
  @Autowired
  private UserExDao userExDao;

  /**
   * {@inheritDoc}
   */
  @Override
  public BasicUserInfo getBasicUserInfo(int userId) throws UserServiceException {
    User user = authUserService.findUserById(userId);
    BasicUserInfo basicUserInof = new BasicUserInfo();
    UserEx userEx = userExDao.findOne(userId);
    if (null == user) {
      // not found
      throw new UserServiceException(ServiceErrorCode.USER_NOT_FOUND, String.format("user [%d] is not found.", userId));
    } else if (null == userEx) {
      // illegal status
      throw new UserServiceException(ServiceErrorCode.USER_IN_ILLEGAL_STATUS, String.format("user [%d] is in illegal status.", userId));
    } else {
      basicUserInof.setId(userId).setUsername(user.getUsername()).setAvatarUrl(userEx.getAvatarUrl())
          .setGender(userEx.getGender()).setPoint(userEx.getPoints());
    }
    return basicUserInof;
  }

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
    return authUserService.isUsernameExists(username);
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
        authUserService.addUser(user);
        
        // save the UserEx as well
        UserEx userEx = new UserEx();
        userEx.setUserId(user.getId());
        userExDao.save(userEx);
        
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
