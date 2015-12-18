package com.elminster.grs.web.vo;

import java.util.Date;

import com.elminster.grs.shared.db.domain.Location;
import com.elminster.grs.shared.db.domain.ProfileDisplayStrategy;
import com.elminster.grs.shared.db.domain.UserEx.Gender;

public class UpdateUserProfileModel {

  private String avatarUrl;
  
  private Location location;
  
  private ProfileDisplayStrategy locationDisplayStrategy;
  
  private Gender gender;
  
  private Date birthday;
  
  private ProfileDisplayStrategy birthdayDisplayStrategy;
  
  private String bio;
  
  private String blogUrl;
  
  private ProfileDisplayStrategy blogDisplayStrategy;
  
  private String weiboUrl;
  
  private ProfileDisplayStrategy weiboDisplayStrategy;
  
  private String qq;
  
  private ProfileDisplayStrategy qqDisplayStrategy;

  /**
   * @return the avatarUrl
   */
  public String getAvatarUrl() {
    return avatarUrl;
  }

  /**
   * @param avatarUrl the avatarUrl to set
   */
  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  /**
   * @return the location
   */
  public Location getLocation() {
    return location;
  }

  /**
   * @param location the location to set
   */
  public void setLocation(Location location) {
    this.location = location;
  }

  /**
   * @return the locationDisplayStrategy
   */
  public ProfileDisplayStrategy getLocationDisplayStrategy() {
    return locationDisplayStrategy;
  }

  /**
   * @param locationDisplayStrategy the locationDisplayStrategy to set
   */
  public void setLocationDisplayStrategy(ProfileDisplayStrategy locationDisplayStrategy) {
    this.locationDisplayStrategy = locationDisplayStrategy;
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
  public void setGender(Gender gender) {
    this.gender = gender;
  }

  /**
   * @return the birthday
   */
  public Date getBirthday() {
    return birthday;
  }

  /**
   * @param birthday the birthday to set
   */
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  /**
   * @return the birthdayDisplayStrategy
   */
  public ProfileDisplayStrategy getBirthdayDisplayStrategy() {
    return birthdayDisplayStrategy;
  }

  /**
   * @param birthdayDisplayStrategy the birthdayDisplayStrategy to set
   */
  public void setBirthdayDisplayStrategy(ProfileDisplayStrategy birthdayDisplayStrategy) {
    this.birthdayDisplayStrategy = birthdayDisplayStrategy;
  }

  /**
   * @return the bio
   */
  public String getBio() {
    return bio;
  }

  /**
   * @param bio the bio to set
   */
  public void setBio(String bio) {
    this.bio = bio;
  }

  /**
   * @return the blogUrl
   */
  public String getBlogUrl() {
    return blogUrl;
  }

  /**
   * @param blogUrl the blogUrl to set
   */
  public void setBlogUrl(String blogUrl) {
    this.blogUrl = blogUrl;
  }

  /**
   * @return the blogDisplayStrategy
   */
  public ProfileDisplayStrategy getBlogDisplayStrategy() {
    return blogDisplayStrategy;
  }

  /**
   * @param blogDisplayStrategy the blogDisplayStrategy to set
   */
  public void setBlogDisplayStrategy(ProfileDisplayStrategy blogDisplayStrategy) {
    this.blogDisplayStrategy = blogDisplayStrategy;
  }

  /**
   * @return the weiboUrl
   */
  public String getWeiboUrl() {
    return weiboUrl;
  }

  /**
   * @param weiboUrl the weiboUrl to set
   */
  public void setWeiboUrl(String weiboUrl) {
    this.weiboUrl = weiboUrl;
  }

  /**
   * @return the weiboDisplayStrategy
   */
  public ProfileDisplayStrategy getWeiboDisplayStrategy() {
    return weiboDisplayStrategy;
  }

  /**
   * @param weiboDisplayStrategy the weiboDisplayStrategy to set
   */
  public void setWeiboDisplayStrategy(ProfileDisplayStrategy weiboDisplayStrategy) {
    this.weiboDisplayStrategy = weiboDisplayStrategy;
  }

  /**
   * @return the qq
   */
  public String getQq() {
    return qq;
  }

  /**
   * @param qq the qq to set
   */
  public void setQq(String qq) {
    this.qq = qq;
  }

  /**
   * @return the qqDisplayStrategy
   */
  public ProfileDisplayStrategy getQqDisplayStrategy() {
    return qqDisplayStrategy;
  }

  /**
   * @param qqDisplayStrategy the qqDisplayStrategy to set
   */
  public void setQqDisplayStrategy(ProfileDisplayStrategy qqDisplayStrategy) {
    this.qqDisplayStrategy = qqDisplayStrategy;
  }
}
