package com.elminster.grs.giantbomb.service;

import com.elminster.common.exception.BaseException;
import com.elminster.common.exception.ErrorCode;

public class GameCollectException extends BaseException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private static final ErrorCode GAME_COLLECT_COMMON_CODE = new ErrorCode("GAMECOLL01", "GAME_COLLECT_COMMON");

  public GameCollectException(Throwable cause) {
    super(GAME_COLLECT_COMMON_CODE, cause);
  }
}
