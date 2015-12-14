package com.elminster.grs.crawler;

import com.elminster.common.exception.BaseException;
import com.elminster.common.exception.ErrorCode;

public class CrawlerException extends BaseException {

  /**
   * 
   */
  private static final long serialVersionUID = -7334255150122703190L;

  public CrawlerException(ErrorCode code, String message, Throwable cause) {
    super(code, message, cause);
  }

  public CrawlerException(ErrorCode code, String message) {
    super(code, message);
  }

  public CrawlerException(ErrorCode code, Throwable cause) {
    super(code, cause);
  }
}
