package com.elminster.grs.web.service;

import org.springframework.security.core.Authentication;

import com.elminster.grs.web.request.vo.LoginJsonModel;
import com.elminster.grs.web.request.vo.RegisterJsonModel;

public interface AuthenticationService {

  public Authentication login(LoginJsonModel login) throws ServiceException;
  
  public boolean isUsernameOccupied(String username) throws ServiceException;
  
  public Authentication register(RegisterJsonModel model) throws ServiceException;
}
