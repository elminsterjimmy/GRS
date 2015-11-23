package com.elminster.grs.web.handler;

import com.elminster.common.exception.BaseException;
import com.elminster.common.exception.ErrorCode;

public class HandlerException extends BaseException {

  /**
   * 
   */
  private static final long serialVersionUID = -7334255150122703190L;

  public HandlerException(ErrorCode code, String message, Throwable cause) {
    super(code, message, cause);
  }

  public HandlerException(ErrorCode code, String message) {
    super(code, message);
  }

  public HandlerException(ErrorCode code, Throwable cause) {
    super(code, cause);
  }
}
