package com.elminster.grs.web.vo.request;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPasswordModel {

  @NotEmpty
  private Integer userId;
  
  @NotEmpty
  private String oldPassword;
  
  @NotEmpty
  private String newPassword;
}
