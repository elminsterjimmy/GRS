package com.elminster.grs.web.service;

import com.elminster.common.exception.ErrorCode;

public class UserServiceException extends ServiceException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public UserServiceException(ErrorCode code, String message, Throwable cause) {
    super(code, message, cause);
  }

  public UserServiceException(ErrorCode code, String message) {
    super(code, message);
  }

  public UserServiceException(ErrorCode code, Throwable cause) {
    super(code, cause);
  }
}
