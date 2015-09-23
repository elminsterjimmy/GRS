package com.elminster.grs.web.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The login details.
 * 
 * @author jgu
 * @version 1.0
 */
public class LoginDetails {

  /** the username. */
  @NotEmpty()
  private String username;
  /** the password. */
  @NotEmpty()
  private String password;
  /** the expiration. */
  private long expiration;
  
  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }
  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
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
  public void setPassword(String password) {
    this.password = password;
  }
  /**
   * @return the expiration
   */
  public long getExpiration() {
    return expiration;
  }
  /**
   * @param expiration the expiration to set
   */
  public void setExpiration(long expiration) {
    this.expiration = expiration;
  }
}
