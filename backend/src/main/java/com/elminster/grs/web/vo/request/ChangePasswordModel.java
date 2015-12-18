package com.elminster.grs.web.vo.request;

import org.hibernate.validator.constraints.NotEmpty;

public class ChangePasswordModel {

  @NotEmpty
  private String oldPassword;
  
  @NotEmpty
  private String newPassword;

  /**
   * @return the oldPassword
   */
  public String getOldPassword() {
    return oldPassword;
  }

  /**
   * @param oldPassword the oldPassword to set
   */
  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  /**
   * @return the newPassword
   */
  public String getNewPassword() {
    return newPassword;
  }

  /**
   * @param newPassword the newPassword to set
   */
  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
}
