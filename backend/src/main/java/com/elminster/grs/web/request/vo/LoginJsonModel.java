package com.elminster.grs.web.request.vo;

import org.hibernate.validator.constraints.NotEmpty;

import com.elminster.common.util.ObjectUtil;

/**
 * The login json model.
 * 
 * @author jgu
 * @version 1.0
 */
public class LoginJsonModel extends BaseJsonModel {

  /** the username. */
  @NotEmpty
  private String username;
  /** the password. */
  @NotEmpty
  private String password;
  
  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }
  /**
   * @param username the username to set
   */
  public LoginJsonModel setUsername(String username) {
    this.username = username;
    return this;
  }
  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }
  /**
   * @param password the password to set
   */
  public LoginJsonModel setPassword(String password) {
    this.password = password;
    return this;
  }
  
  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return ObjectUtil.buildToStringByReflect(this);
  }
}
