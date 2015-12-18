package com.elminster.grs.shared.db.domain;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
  @Column(name="id")
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
  
  @Column(length=4096)
  private String bio;
  
  @Enumerated(EnumType.ORDINAL)
  @Column(length=1)
  private BloadType booldType;
  
  @Column(length=1024)
  private String blogUrl;
  
  @Column(length=1024)
  private String weiboUrl;
  
  @Column(length=64)
  private String qq;
  
  @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)    
  @JoinTable(name = "comm_user_tag",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id") },     
      inverseJoinColumns = { @JoinColumn(name = "tag_id", referencedColumnName = "id")     
  })
  private Set<UserTag> tags;
  
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
   * @return the tags
   */
  public Set<UserTag> getTags() {
    return tags;
  }

  /**
   * @param tags the tags to set
   */
  public void setTags(Set<UserTag> tags) {
    this.tags = tags;
  }

  public static enum Gender {
    UNKNOWN,
    MALE,
    FEMALE
  }
}
