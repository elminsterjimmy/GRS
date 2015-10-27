package com.elminster.grs.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elminster.grs.web.dao.UserExDao;
import com.elminster.grs.web.domain.UserEx;
import com.elminster.grs.web.response.vo.BasicUserInfo;
import com.elminster.grs.web.service.ServiceErrorCode;
import com.elminster.grs.web.service.UserServiceException;
import com.elminster.grs.web.service.UserService;
import com.elminster.spring.security.domain.User;
import com.elminster.spring.security.service.AuthUserService;

/**
 * The user service implememntaion.
 * 
 * @author jgu
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserExDao userExDao;
  @Autowired
  private AuthUserService authUserService;

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

}
