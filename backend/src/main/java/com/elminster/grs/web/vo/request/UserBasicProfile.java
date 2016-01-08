package com.elminster.grs.web.vo.request;

import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserBasicProfile {

  private Integer userId;
  
  @Email
  private String email;

  private String mobile;

  @NotEmpty
  private Integer gender;

  private Date birthday;

  private Integer livePlaceLv1;

  private Integer livePlaceLv2;

  private Integer livePlaceLv3;

  private String bio;

  private String blog;

  private String weibo;

  private String qq;
  
  @NotEmpty
  private Integer bloodType;
}
