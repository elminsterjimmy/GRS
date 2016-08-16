package com.elminster.grs.web.vo.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserGameProfile {

  @NotNull
  @JsonProperty("id")
  private Integer userId;
  
  private String psnUsername;
  
  private String liveUsername;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
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
}
