package com.elminster.grs.crawler.impl;

import com.elminster.common.exception.ErrorCode;

final public class CrawlerErrorCode {

  public static final ErrorCode UNEXCEPTED_EXCEPTION = new ErrorCode("HE000", "UNEXCEPTED_EXCEPTION");
  public static final ErrorCode RETRIEVE_PSN_PROFILE_EXCEPTION = new ErrorCode("HE001", "RETRIEVE_PSN_PROFILE_EXCEPTION");
  public static final ErrorCode RETRIEVE_PSN_GAME_LIST_EXCEPTION = new ErrorCode("HE002", "RETRIEVE_PSN_GAME_LIST_EXCEPTION");
  public static final ErrorCode RETRIEVE_PSN_GAME_TROPHIES_EXCEPTION = new ErrorCode("HE003", "RETRIEVE_PSN_GAME_TROPHIES_EXCEPTION");
  public static final ErrorCode UPDATE_PSN_INFORMATION_EXCEPTION = new ErrorCode("HE100", "UPDATE_PSN_INFORMATION_EXCEPTION");
}
