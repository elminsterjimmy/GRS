package com.elminster.grs.web.model;

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
  private long currentTs;

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
}
