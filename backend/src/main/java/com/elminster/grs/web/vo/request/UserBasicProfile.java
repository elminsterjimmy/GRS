package com.elminster.grs.web.vo.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@NoArgsConstructor
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
}
