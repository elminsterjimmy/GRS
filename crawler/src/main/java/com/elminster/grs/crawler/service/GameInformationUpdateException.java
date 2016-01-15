package com.elminster.grs.crawler.service;

import com.elminster.common.exception.BaseException;
import com.elminster.common.exception.ErrorCode;

public class GameInformationUpdateException extends BaseException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public GameInformationUpdateException(ErrorCode code, String message, Throwable cause) {
    super(code, message, cause);
  }

  public GameInformationUpdateException(ErrorCode code, String message) {
    super(code, message);
  }

  public GameInformationUpdateException(ErrorCode code, Throwable cause) {
    super(code, cause);
  }
}
