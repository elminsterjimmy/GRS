package com.elminster.grs.giantbomb.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ApiCounterManager {

  private static final Log logger = LogFactory.getLog(ApiCounterManager.class);
  
  public static final ApiCounterManager INSTANCE = new ApiCounterManager();
  
  private Map<String, ApiCounter> managedApiCounters = new HashMap<>();
  
  private ApiCounterManager() {}
  
  public ApiCounter getApiCounter(String name, int threshold, long resetPeriod) {
    ApiCounter apiCounter = new ApiCounter(name, threshold, resetPeriod);
    return put2Manager(name, apiCounter);
  }
  
  public ApiCounter getApiCounter(String name, String username, String password, int threshold, long resetPeriod) {
    ApiCounter apiCounter = new ApiCounter(name, username, password, threshold, resetPeriod);
    return put2Manager(name, apiCounter);
  }
  
  public ApiCounter getApiCounter(String name, String apiKey, int threshold, long resetPeriod) {
    ApiCounter apiCounter = new ApiCounter(name, apiKey, threshold, resetPeriod);
    return put2Manager(name, apiCounter);
  }
  
  private ApiCounter put2Manager(String name, ApiCounter apiCounter) {
    managedApiCounters.put(name, apiCounter);
    return apiCounter;
  }
  
  public Collection<ApiCounter> listApiCounters() {
    return managedApiCounters.values();
  }
}
