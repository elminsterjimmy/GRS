package com.elminster.grs.web.handler;

import com.elminster.common.exception.ErrorCode;

public class UserInformationUpdateException extends HandlerException {
  

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public UserInformationUpdateException(ErrorCode code, String message) {
    super(code, message);
  }

  public UserInformationUpdateException(ErrorCode code, String message, Throwable cause) {
    super(code, message, cause);
  }

  public UserInformationUpdateException(ErrorCode code, Throwable cause) {
    super(code, cause);
  }
}
