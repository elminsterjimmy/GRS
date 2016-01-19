package com.elminster.grs.web.vo.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserGameProfile {

  @NotNull
  @JsonProperty("id")
  private Integer userId;
  
  private String psnUsername;
  
  private String liveUsername;
}
