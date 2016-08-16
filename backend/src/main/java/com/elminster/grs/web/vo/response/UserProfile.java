package com.elminster.grs.web.vo.response;

import java.util.Date;

public class UserProfile extends BasicUserInfo {

  private Date birthday;

  private Integer gender;

  private Integer bloodType;

  private Integer livePlaceLv1;

  private Integer livePlaceLv2;

  private Integer livePlaceLv3;

  private String mobile;

  private String email;

  private String bio;

  private String blog;

  private String weibo;

  private String qq;

  private String psnUsername;

  private String liveUsername;

  private String[] tags;
  
  public UserProfile() {
  }

  public UserProfile(Date birthday, Integer gender, Integer bloodType, Integer livePlaceLv1, Integer livePlaceLv2, Integer livePlaceLv3, String mobile, String email, String bio,
      String blog, String weibo, String qq, String psnUsername, String liveUsername, String[] tags) {
    super();
    this.birthday = birthday;
    this.gender = gender;
    this.bloodType = bloodType;
    this.livePlaceLv1 = livePlaceLv1;
    this.livePlaceLv2 = livePlaceLv2;
    this.livePlaceLv3 = livePlaceLv3;
    this.mobile = mobile;
    this.email = email;
    this.bio = bio;
    this.blog = blog;
    this.weibo = weibo;
    this.qq = qq;
    this.psnUsername = psnUsername;
    this.liveUsername = liveUsername;
    this.tags = tags;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public Integer getBloodType() {
    return bloodType;
  }

  public void setBloodType(Integer bloodType) {
    this.bloodType = bloodType;
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

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public String getPsnUsername() {
    return psnUsername;
  }

  public void setPsnUsername(String psnUsername) {
    this.psnUsername = psnUsername;
  }

  public String getLiveUsername() {
    return liveUsername;
  }

  public void setLiveUsername(String liveUsername) {
    this.liveUsername = liveUsername;
  }

  public String[] getTags() {
    return tags;
  }

  public void setTags(String[] tags) {
    this.tags = tags;
  }

  public static class UserProfileBuilder {
    private Date birthday;
    private Integer gender;
    private Integer bloodType;
    private Integer livePlaceLv1;
    private Integer livePlaceLv2;
    private Integer livePlaceLv3;
    private String mobile;
    private String email;
    private String bio;
    private String blog;
    private String weibo;
    private String qq;
    private String psnUsername;
    private String liveUsername;
    private String[] tags;

    public UserProfileBuilder() {
    }

    public UserProfileBuilder(UserProfile bean) {
      this.birthday = bean.birthday;
      this.gender = bean.gender;
      this.bloodType = bean.bloodType;
      this.livePlaceLv1 = bean.livePlaceLv1;
      this.livePlaceLv2 = bean.livePlaceLv2;
      this.livePlaceLv3 = bean.livePlaceLv3;
      this.mobile = bean.mobile;
      this.email = bean.email;
      this.bio = bean.bio;
      this.blog = bean.blog;
      this.weibo = bean.weibo;
      this.qq = bean.qq;
      this.psnUsername = bean.psnUsername;
      this.liveUsername = bean.liveUsername;
      this.tags = bean.tags;
    }

    public UserProfileBuilder withBirthday(Date birthday) {
      this.birthday = birthday;
      return this;
    }

    public UserProfileBuilder withGender(Integer gender) {
      this.gender = gender;
      return this;
    }

    public UserProfileBuilder withBloodType(Integer bloodType) {
      this.bloodType = bloodType;
      return this;
    }

    public UserProfileBuilder withLivePlaceLv1(Integer livePlaceLv1) {
      this.livePlaceLv1 = livePlaceLv1;
      return this;
    }

    public UserProfileBuilder withLivePlaceLv2(Integer livePlaceLv2) {
      this.livePlaceLv2 = livePlaceLv2;
      return this;
    }

    public UserProfileBuilder withLivePlaceLv3(Integer livePlaceLv3) {
      this.livePlaceLv3 = livePlaceLv3;
      return this;
    }

    public UserProfileBuilder withMobile(String mobile) {
      this.mobile = mobile;
      return this;
    }

    public UserProfileBuilder withEmail(String email) {
      this.email = email;
      return this;
    }

    public UserProfileBuilder withBio(String bio) {
      this.bio = bio;
      return this;
    }

    public UserProfileBuilder withBlog(String blog) {
      this.blog = blog;
      return this;
    }

    public UserProfileBuilder withWeibo(String weibo) {
      this.weibo = weibo;
      return this;
    }

    public UserProfileBuilder withQq(String qq) {
      this.qq = qq;
      return this;
    }

    public UserProfileBuilder withPsnUsername(String psnUsername) {
      this.psnUsername = psnUsername;
      return this;
    }

    public UserProfileBuilder withLiveUsername(String liveUsername) {
      this.liveUsername = liveUsername;
      return this;
    }

    public UserProfileBuilder withTags(String[] tags) {
      this.tags = tags;
      return this;
    }

    public UserProfile build() {
      return new UserProfile(birthday, gender, bloodType, livePlaceLv1, livePlaceLv2, livePlaceLv3, mobile, email, bio, blog, weibo, qq, psnUsername, liveUsername, tags);
    }
  }

  public static UserProfileBuilder builder() {
    return new UserProfileBuilder();
  }

}
