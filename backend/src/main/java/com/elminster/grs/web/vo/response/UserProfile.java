package com.elminster.grs.web.vo.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile extends BasicUserInfo {

  private Date birthday;

  private Integer gender;
  
  private Integer bloodType;
  
  private Integer livePlaceLv1;
  
  private Integer livePlaceLv2;
  
  private Integer livePlaceLv3;
  
  private String moblie;
  
  private String email;
  
  private String bio;
  
  private String blog;
  
  private String weibo;
  
  private String qq;
  
  private String psnUsername;
  
  private String liveUsername;
  
  private String[] tags;
}
