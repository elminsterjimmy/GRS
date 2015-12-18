package com.elminster.grs.web.response.vo;

import com.elminster.grs.shared.db.domain.UserEx.Gender;

public class BasicUserInfo {

  private int id;
  private String username;
  private String avatarUrl;
  private Gender gender;
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
   * @return the gender
   */
  public Gender getGender() {
    return gender;
  }
  /**
   * @param gender the gender to set
   */
  public BasicUserInfo setGender(Gender gender) {
    this.gender = gender;
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
