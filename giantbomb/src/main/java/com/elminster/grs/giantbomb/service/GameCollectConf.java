package com.elminster.grs.giantbomb.service;

import com.elminster.grs.giantbomb.util.ApiCounter;

public class GameCollectConf {

  private ApiCounter apiCounter;
  private int from = 0;
  private int to = 0;

  public int getFrom() {
    return from;
  }

  public void setFrom(int from) {
    this.from = from;
  }

  public int getTo() {
    return to;
  }

  public void setTo(int to) {
    this.to = to;
  }
  
  public boolean isEndless() {
    return 0 == to;
  }

  public ApiCounter getApiCounter() {
    return apiCounter;
  }

  public void setApiCounter(ApiCounter apiCounter) {
    this.apiCounter = apiCounter;
  }
}
