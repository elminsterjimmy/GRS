package com.elminster.grs.web.vo.response;

public class BasicUserInfo {

  private int id;
  private String username;
  private String avatarUrl;
  private int point;
  
  /**
   * @return the id
   */
  public int getId() {
    return id;
  }
  /**
   * @param id the id to set
   */
  public BasicUserInfo setId(int id) {
    this.id = id;
    return this;
  }
  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }
  /**
   * @param username the username to set
   */
  public BasicUserInfo setUsername(String username) {
    this.username = username;
    return this;
  }
  /**
   * @return the avatarUrl
   */
  public String getAvatarUrl() {
    return avatarUrl;
  }
  /**
   * @param avatarUrl the avatarUrl to set
   */
  public BasicUserInfo setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
    return this;
  }
  /**
   * @return the point
   */
  public int getPoint() {
    return point;
  }
  /**
   * @param point the point to set
   */
  public BasicUserInfo setPoint(int point) {
    this.point = point;
    return this;
  }
}
