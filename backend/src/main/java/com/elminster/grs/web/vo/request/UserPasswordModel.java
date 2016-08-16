package com.elminster.grs.web.vo.request;

import org.hibernate.validator.constraints.NotEmpty;

public class UserPasswordModel {

  @NotEmpty
  private Integer userId;
  
  @NotEmpty
  private String oldPassword;
  
  @NotEmpty
  private String newPassword;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
}
