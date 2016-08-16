package com.elminster.grs.web.vo.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserBasicProfile {

  @NotNull
  @JsonProperty("id")
  private Integer userId;
  
  @Email
  private String email;

  private String mobile;

  @NotNull
  private Integer gender;

  private Date birthday;

  private Integer livePlaceLv1;

  private Integer livePlaceLv2;

  private Integer livePlaceLv3;

  private String bio;

  private String blog;

  private String weibo;

  private String qq;
  
  @NotNull
  private Integer bloodType;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public Integer getLivePlaceLv1() {
    return livePlaceLv1;
  }

  public void setLivePlaceLv1(Integer livePlaceLv1) {
    this.livePlaceLv1 = livePlaceLv1;
  }

  public Integer getLivePlaceLv2() {
    return livePlaceLv2;
  }

  public void setLivePlaceLv2(Integer livePlaceLv2) {
    this.livePlaceLv2 = livePlaceLv2;
  }

  public Integer getLivePlaceLv3() {
    return livePlaceLv3;
  }

  public void setLivePlaceLv3(Integer livePlaceLv3) {
    this.livePlaceLv3 = livePlaceLv3;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getBlog() {
    return blog;
  }

  public void setBlog(String blog) {
    this.blog = blog;
  }

  public String getWeibo() {
    return weibo;
  }

  public void setWeibo(String weibo) {
    this.weibo = weibo;
  }

  public String getQq() {
    return qq;
  }

  public void setQq(String qq) {
    this.qq = qq;
  }

  public Integer getBloodType() {
    return bloodType;
  }

  public void setBloodType(Integer bloodType) {
    this.bloodType = bloodType;
  }
}
