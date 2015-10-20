package com.elminster.grs.web.service;

import com.elminster.common.exception.ErrorCode;

final public class ServiceErrorCode {
  
  public static final ErrorCode UNEXCEPTED_EXCEPTION = new ErrorCode("SE000", "UNEXCEPTED_EXCEPTION");
  public static final ErrorCode LOGIN_INCORRECT_USERNAME_OR_PASSWORD = new ErrorCode("AE001", "LOGIN_INCORRECT_USERNAME_OR_PASSWORD");
  public static final ErrorCode LOGIN_ACCOUNT_DISABLED = new ErrorCode("AE002", "LOGIN_ACCOUNT_DISABLED");
  public static final ErrorCode REGISTER_USERNAME_OCCUPIED = new ErrorCode("RE001", "REGISTER_USERNAME_OCCUPIED");
}
