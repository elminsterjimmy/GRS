package com.elminster.grs.web.service;

import org.springframework.security.core.Authentication;

import com.elminster.grs.web.vo.request.LoginJsonModel;
import com.elminster.grs.web.vo.request.RegisterJsonModel;
import com.elminster.grs.web.vo.request.UserBasicProfile;
import com.elminster.grs.web.vo.request.UserGameProfile;
import com.elminster.grs.web.vo.response.BasicUserInfo;
import com.elminster.grs.web.vo.response.UserProfile;

/**
 * The user service.
 * 
 * @author jgu
 * @version 1.0
 */
public interface UserService {

  /**
   * Get user's basic information.
   * @param userId the user id
   * @return user's basic information
   * @throws UserServiceException on error
   */
  public BasicUserInfo getBasicUserInfo(int userId) throws UserServiceException;
  
  /**
   * Get user's basic profile.
   * @param userId the user id
   * @return user's extra information
   * @throws UserServiceException on error
   */
  public UserProfile getUserProfile(int userId) throws UserServiceException;
  
  /**
   * Update the user's basic profile.
   * @param userBasicProfile the user basic profile
   * @throws UserServiceException on error
   */
  public void updateUserBasicProfile(UserBasicProfile userBasicProfile) throws UserServiceException;
  
  /**
   * Update the user's game profile.
   * @param userBasicProfile the user basic profile
   * @throws UserServiceException on error
   */
  public void updateUserGameProfile(UserGameProfile userGameProfile) throws UserServiceException;

  /**
   * User login.
   * @param login login model
   * @return login authentication
   * @throws ServiceException on error
   */
  public Authentication login(LoginJsonModel login) throws ServiceException;

  /**
   * Check whether the username is occupied.
   * @return username the username to check
   * @throws ServiceException on error
   */
  public boolean isUsernameOccupied(String username) throws ServiceException;

  /**
   * User register.
   * @param model the register model
   * @return login authentication
   * @throws ServiceException on error
   */
  public Authentication register(RegisterJsonModel model) throws ServiceException;
  
  /**
   * Update user's password.
   * @param userId the user id
   * @param oldPassword the old password
   * @param newPassword the new password
   * @throws UserServiceException on error
   */
  public void updatePassword(int userId, String oldPassword, String newPassword) throws UserServiceException;
  
  /**
   * Update user's basic information.
   * @param basicUserInfo the user's basic information
   * @throws UserServiceException on error
   */
  public void updateBasicUserInfo(BasicUserInfo basicUserInfo) throws UserServiceException;
  
  /**
   * Update user's extra information.
   * @param basicUserInfo the user's extra information
   * @throws UserServiceException on error
   */
  public void updateExtraUserInfo() throws UserServiceException;
  
  /**
   * Delete a user.
   * @param userId the user id
   * @throws UserServiceException on error
   */
  public void deleteUser(int userId) throws UserServiceException;
}
