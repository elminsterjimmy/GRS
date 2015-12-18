package com.elminster.grs.web.vo.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Base JSON Model.
 * 
 * @author jgu
 * @version 1.0
 */
public class BaseJsonModel {

  /**
   * The current timestamp.
   */
  @JsonProperty("_")
  @NotNull
  private Long currentTs;
  
  /** 
   * The ip come from.
   * TODO Need to be set automatically.
   */
  private String ipComeFrom;


  /**
   * @return the currentTs
   */
  public long getCurrentTs() {
    return currentTs;
  }

  /**
   * @param currentTs the currentTs to set
   */
  public void setCurrentTs(long currentTs) {
    this.currentTs = currentTs;
  }
  
  /**
   * @return the ipComeFrom
   */
  public String getIpComeFrom() {
    return ipComeFrom;
  }
  /**
   * @param ipComeFrom the ipComeFrom to set
   */
  public void setIpComeFrom(String ipComeFrom) {
    this.ipComeFrom = ipComeFrom;
  }
}
