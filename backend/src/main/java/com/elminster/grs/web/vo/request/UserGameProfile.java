package com.elminster.grs.web.vo.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserGameProfile {

  private Integer userId;
  
  private String psnUsername;
  
  private String liveUsername;
}
