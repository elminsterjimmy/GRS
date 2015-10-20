package com.elminster.grs.web.service;

import com.elminster.common.exception.ErrorCode;

public class RegisterException extends ServiceException {

  /**
   * 
   */
  private static final long serialVersionUID = 1563108550153094638L;

  public RegisterException(ErrorCode code, String message, Throwable cause) {
    super(code, message, cause);
  }

  public RegisterException(ErrorCode code, String message) {
    super(code, message);
  }

  public RegisterException(ErrorCode code, Throwable cause) {
    super(code, cause);
  }
}
