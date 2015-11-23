package com.elminster.grs.web.service;

import com.elminster.common.exception.ErrorCode;

final public class ServiceErrorCode {
  
  public static final ErrorCode UNEXCEPTED_EXCEPTION = new ErrorCode("SE000", "UNEXCEPTED_EXCEPTION");
  public static final ErrorCode LOGIN_INCORRECT_USERNAME_OR_PASSWORD = new ErrorCode("AE001", "LOGIN_INCORRECT_USERNAME_OR_PASSWORD");
  public static final ErrorCode LOGIN_ACCOUNT_DISABLED = new ErrorCode("AE002", "LOGIN_ACCOUNT_DISABLED");
  public static final ErrorCode REGISTER_USERNAME_OCCUPIED = new ErrorCode("RE001", "REGISTER_USERNAME_OCCUPIED");
  public static final ErrorCode USER_NOT_FOUND = new ErrorCode("US001", "USER_NOT_FOUND");
  public static final ErrorCode USER_IN_ILLEGAL_STATUS = new ErrorCode("US002", "USER_IN_ILLEGAL_STATUS");
  public static final ErrorCode UPDATE_USER_GAME_INFO_EXCEPTION = new ErrorCode("UD001", "UPDATE_USER_GAME_INFO_EXCEPTION");
}
