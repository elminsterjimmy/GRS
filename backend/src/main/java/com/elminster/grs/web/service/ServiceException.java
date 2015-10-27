package com.elminster.grs.web.service;

import com.elminster.common.exception.BaseException;
import com.elminster.common.exception.ErrorCode;

public class ServiceException extends BaseException {

  /**
   * 
   */
  private static final long serialVersionUID = -1144843617709498343L;

  public ServiceException(ErrorCode code, String message, Throwable cause) {
    super(code, message, cause);
  }

  public ServiceException(ErrorCode code, String message) {
    super(code, message);
  }

  public ServiceException(ErrorCode code, Throwable cause) {
    super(code, cause);
  }
  
}
