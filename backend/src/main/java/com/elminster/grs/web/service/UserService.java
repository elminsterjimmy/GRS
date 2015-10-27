package com.elminster.grs.web.service;

import com.elminster.grs.web.response.vo.BasicUserInfo;

public interface UserService {

  public BasicUserInfo getBasicUserInfo(int userId) throws UserServiceException;
}
