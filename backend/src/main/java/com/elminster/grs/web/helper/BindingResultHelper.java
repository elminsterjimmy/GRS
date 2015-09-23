package com.elminster.grs.web.helper;

import org.springframework.validation.BindingResult;

public class BindingResultHelper {

  public static final BindingResultHelper INSTANCE = new BindingResultHelper();
  
  private BindingResultHelper() {}

  public String buildErrorString(BindingResult bindingResult) {
    // TODO
    return null;
  }
  
}
