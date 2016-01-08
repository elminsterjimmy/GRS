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
import com.elminster.grs.shared.db.dao.LocationDao;
import com.elminster.grs.shared.db.dao.UserExDao;
import com.elminster.grs.shared.db.dao.UserGameMetaDao;
import com.elminster.grs.shared.db.domain.Location;
import com.elminster.grs.shared.db.domain.UserEx;
import com.elminster.grs.shared.db.domain.UserEx.BloadType;
import com.elminster.grs.shared.db.domain.UserEx.Gender;
import com.elminster.grs.shared.db.domain.UserGameMeta;
import com.elminster.grs.web.service.LoginException;
import com.elminster.grs.web.service.RegisterException;
import com.elminster.grs.web.service.ServiceErrorCode;
import com.elminster.grs.web.service.ServiceException;
import com.elminster.grs.web.service.UserService;
import com.elminster.grs.web.service.UserServiceException;
import com.elminster.grs.web.vo.request.LoginJsonModel;
import com.elminster.grs.web.vo.request.RegisterJsonModel;
import com.elminster.grs.web.vo.request.UserBasicProfile;
import com.elminster.grs.web.vo.request.UserGameProfile;
import com.elminster.grs.web.vo.response.BasicUserInfo;
import com.elminster.grs.web.vo.response.UserProfile;
import com.elminster.grs.web.vo.response.UserProfile.UserProfileBuilder;
import com.elminster.spring.security.domain.User;
import com.elminster.spring.security.model.UserDetailsImpl;
import com.elminster.spring.security.service.AuthUserService;
import com.elminster.spring.security.service.TokenAuthenticationService;

/**
 * The user service implementation.
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
  @Autowired
  private UserGameMetaDao userGameMetaDao;
  @Autowired
  private LocationDao locationDao;

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
      throw new UserServiceException(ServiceErrorCode.USER_IN_ILLEGAL_STATUS, String.format(
          "user [%d] is in illegal status.", userId));
    } else {
      basicUserInof.setId(userId).setUsername(user.getUsername()).setAvatarUrl(userEx.getAvatarUrl())
          .setPoint(userEx.getPoints());
    }
    return basicUserInof;
  }

  /**
   * {@inheritDoc}
   */
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
        logger.info(message);
        throw new LoginException(ServiceErrorCode.LOGIN_INCORRECT_USERNAME_OR_PASSWORD, message);
      }
    } catch (Exception e) {
      if (e instanceof LoginException) {
        throw e;
      }
      String message = "Login Failed. Internet server error. Cause: " + ExceptionUtil.getFullStackTrace(e);
      logger.error(message);
      throw new ServiceException(ServiceErrorCode.UNEXCEPTED_EXCEPTION, message, e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isUsernameOccupied(String username) throws ServiceException {
    return authUserService.isUsernameExists(username);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Transactional
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

        Integer userId = user.getId();
        // save the UserEx as well
        UserEx userEx = new UserEx();
        userEx.setUserId(userId);
        userExDao.save(userEx);

        // save the user game meta
        UserGameMeta userGameMeta = new UserGameMeta();
        userGameMeta.setUserId(userId);
        userGameMetaDao.save(userGameMeta);

        UserDetails details = new UserDetailsImpl(user);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            details.getUsername(), details.getPassword(), details.getAuthorities());
        authentication.setDetails(details);
        return authentication;
      } else {
        // register failed: username occupied
        String message = "Register Failed. Username already exist.";
        logger.info(message);
        throw new RegisterException(ServiceErrorCode.REGISTER_USERNAME_OCCUPIED, message);
      }
    } catch (Exception e) {
      if (e instanceof RegisterException) {
        throw e;
      }
      String message = "Register Failed. Internet server error. Cause: " + ExceptionUtil.getFullStackTrace(e);
      logger.error(message);
      throw new ServiceException(ServiceErrorCode.UNEXCEPTED_EXCEPTION, message, e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UserProfile getUserProfile(int userId) throws UserServiceException {
    User user = authUserService.findUserById(userId);
    UserProfile basicUserProfile = new UserProfile();
    UserEx userEx = userExDao.findOne(userId);
    UserGameMeta userGameMeta = userGameMetaDao.findOne(userId);
    if (null == user) {
      // not found
      throw new UserServiceException(ServiceErrorCode.USER_NOT_FOUND, String.format("user [%d] is not found.", userId));
    } else if (null == userEx) {
      // illegal status
      throw new UserServiceException(ServiceErrorCode.USER_IN_ILLEGAL_STATUS, String.format(
          "user [%d] is in illegal status.", userId));
    } else {
      UserProfileBuilder builder = UserProfile.builder();
      builder.bio(userEx.getBio()).birthday(userEx.getBirthday()).blog(userEx.getBlogUrl()).email(user.getEmail())
          .gender(userEx.getGender().ordinal()).moblie(user.getMobile()).qq(userEx.getQq()).weibo(userEx.getWeiboUrl())
          .bloodType(userEx.getBooldType().ordinal());
      Location location = userEx.getLivedLocation();
      fillLocation(builder, location);
      if (null != userGameMeta) {
        builder.psnUsername(userGameMeta.getPsnId()).liveUsername(userGameMeta.getLiveId());
      }
      basicUserProfile = builder.build();
      basicUserProfile.setId(userId).setUsername(user.getUsername()).setAvatarUrl(userEx.getAvatarUrl())
          .setPoint(userEx.getPoints());
    }
    return basicUserProfile;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Transactional
  public void updateBasicUserInfo(BasicUserInfo basicUserInfo) throws UserServiceException {
    // TODO Auto-generated method stub
    
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Transactional
  public void updateExtraUserInfo() throws UserServiceException {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Transactional
  public void deleteUser(int userId) throws UserServiceException {
    // TODO Auto-generated method stub

  }

  @Override
  public void updatePassword(int userId, String oldPassword, String newPassword) throws UserServiceException {
    User user = authUserService.findUserById(userId);
    if (oldPassword.equals(user.getPassword())) {
      user.setPassword(newPassword);
      authUserService.saveUser(user);
    } else {
      String message = String.format("Update password Failed. Incorrect password for user [%s].", user.getUsername());
      logger.info(message);
      throw new UserServiceException(ServiceErrorCode.UPDATE_PASSWORD_INCORRECT_PASSWORD, message);
    }
  }

  @Override
  @Transactional
  public void updateUserBasicProfile(UserBasicProfile userBasicProfile) throws UserServiceException {
    int userId = userBasicProfile.getUserId();
    User user = authUserService.findUserById(userId);
    UserEx userEx = userExDao.findOne(userId);
    if (null == user) {
      // not found
      throw new UserServiceException(ServiceErrorCode.USER_NOT_FOUND, String.format("user [%d] is not found.", userId));
    }
    if (null == userEx) {
      // illegal status
      throw new UserServiceException(ServiceErrorCode.USER_IN_ILLEGAL_STATUS, String.format(
          "user [%d] is in illegal status.", userId));
    }
    user.setEmail(userBasicProfile.getEmail());
    user.setMobile(userBasicProfile.getMobile());
    authUserService.saveUser(user);
    
    userEx.setBio(userBasicProfile.getBio());
    userEx.setBirthday(userEx.getBirthday());
    userEx.setBlogUrl(userBasicProfile.getBlog());
    userEx.setGender(Gender.values()[userBasicProfile.getGender()]);
    
    Integer locationId = null;
    if (null != userBasicProfile.getLivePlaceLv3()) {
      locationId = userBasicProfile.getLivePlaceLv3();
    } else if (null != userBasicProfile.getLivePlaceLv2()) {
      locationId = userBasicProfile.getLivePlaceLv2();
    } else if (null != userBasicProfile.getLivePlaceLv1()) {
      locationId = userBasicProfile.getLivePlaceLv1();
    }
    Location location = null;
    if (null != locationId) {
      location = locationDao.findOne(locationId);
    }
    userEx.setLivedLocation(location);
    userEx.setQq(userBasicProfile.getQq());
    userEx.setWeiboUrl(userBasicProfile.getWeibo());
    userEx.setBooldType(BloadType.values()[userBasicProfile.getBloodType()]);
    userExDao.save(userEx);
  }
  
  @Override
  @Transactional
  public void updateUserGameProfile(UserGameProfile userGameProfile) throws UserServiceException {
    int userId = userGameProfile.getUserId();
    User user = authUserService.findUserById(userId);
    UserGameMeta userGameMeta = userGameMetaDao.findOne(userId);
    if (null == user) {
      // not found
      throw new UserServiceException(ServiceErrorCode.USER_NOT_FOUND, String.format("user [%d] is not found.", userId));
    }
    if (null == userGameMeta) {
      // illegal status
      throw new UserServiceException(ServiceErrorCode.USER_IN_ILLEGAL_STATUS, String.format(
          "user [%d] is in illegal status.", userId));
    }
    userGameMeta.setPsnId(userGameProfile.getPsnUsername());
    userGameMeta.setLiveId(userGameProfile.getLiveUsername());
    userGameMetaDao.save(userGameMeta);
  }
  
  
  private void fillLocation(UserProfileBuilder builder, Location location) {
    if (null != location) {
      int level = location.getLevel();
      int[] ids = new int[level];
      while ((level = location.getLevel()) > 0) {
        ids[level - 1] = location.getId();
        location = location.getParent();
      }
      builder.livePlaceLv1(ids.length > 1 ? ids[0] : null).
              livePlaceLv2(ids.length > 2 ? ids[1] : null).
              livePlaceLv3(ids.length > 3 ? ids[2] : null);
    }
  }
}
