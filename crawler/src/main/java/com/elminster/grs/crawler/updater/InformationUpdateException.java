package com.elminster.grs.crawler.updater;

import com.elminster.common.exception.BaseException;
import com.elminster.common.exception.ErrorCode;

public class InformationUpdateException extends BaseException {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public InformationUpdateException(ErrorCode code, String message) {
    super(code, message);
  }

  public InformationUpdateException(ErrorCode code, String message, Throwable cause) {
    super(code, message, cause);
  }

  public InformationUpdateException(ErrorCode code, Throwable cause) {
    super(code, cause);
  }
}
