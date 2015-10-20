package com.elminster.grs.web.service;

import com.elminster.common.exception.ErrorCode;

public class LoginException extends ServiceException {

  /**
   * 
   */
  private static final long serialVersionUID = 4667295717692831580L;
  
  public LoginException(ErrorCode code, String message) {
    super(code, message);
  }

  public LoginException(ErrorCode code, String message, Throwable cause) {
    super(code, message, cause);
  }

  public LoginException(ErrorCode code, Throwable cause) {
    super(code, cause);
  }
}
