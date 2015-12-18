package com.elminster.grs.web.vo.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecordAvailableCheckModel extends BaseJsonModel {

  @JsonProperty("v")
  @NotNull
  private String recordToCheck;

  /**
   * @return the recordToCheck
   */
  public String getRecordToCheck() {
    return recordToCheck;
  }

  /**
   * @param recordToCheck the recordToCheck to set
   */
  public void setRecordToCheck(String recordToCheck) {
    this.recordToCheck = recordToCheck;
  }
}
