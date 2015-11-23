package com.elminster.grs.web.service;

import com.elminster.common.exception.ErrorCode;

public class CollectionException extends ServiceException {

  /**
   * 
   */
  private static final long serialVersionUID = 1797841550971629996L;

  public CollectionException(ErrorCode code, String message) {
    super(code, message);
  }

  public CollectionException(ErrorCode code, String message, Throwable cause) {
    super(code, message, cause);
  }

  public CollectionException(ErrorCode code, Throwable cause) {
    super(code, cause);
  }
}
