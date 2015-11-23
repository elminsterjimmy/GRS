package com.elminster.grs.web.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Not use <code>@Inheritance</code>.
 * 
 * @author jgu
 *
 */
@Entity
@Table(name = "users_ex")
public class UserEx implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7667917771172375256L;

  @Id
  @Column
  private Integer userId;
  
  @Column(nullable=false, length=1024)
  private String avatarUrl;
  
  @Column(nullable=true)
  @Temporal(TemporalType.DATE)
  private Date birthday;
  
  @Enumerated(EnumType.ORDINAL)
  @Column(nullable=false, length=1)
  private Gender gender = Gender.UNKNOWN;
  
  @Column(nullable=false)
  private Integer points = 0;
  
  @ManyToOne
  @JoinColumn(name="livedLocation")
  private Location livedLocation;
  

  /**
   * @return the userId
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * @param userId the userId to set
   */
  public void setUserId(Integer userId) {
    this.userId = userId;
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
  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
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
   * @return the points
   */
  public int getPoints() {
    return points;
  }

  /**
   * @param points the points to set
   */
  public void setPoints(int points) {
    this.points = points;
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
   * @param points the points to set
   */
  public void setPoints(Integer points) {
    this.points = points;
  }

  /**
   * @return the livedLocation
   */
  public Location getLivedLocation() {
    return livedLocation;
  }

  /**
   * @param livedLocation the livedLocation to set
   */
  public void setLivedLocation(Location livedLocation) {
    this.livedLocation = livedLocation;
  }
}
