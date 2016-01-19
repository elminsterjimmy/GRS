package com.elminster.grs.web.service;

import com.elminster.common.exception.ErrorCode;

final public class ServiceErrorCode {
  // common
  public static final ErrorCode UNEXCEPTED_EXCEPTION = new ErrorCode("SE000", "UNEXCEPTED_EXCEPTION");
  public static final ErrorCode DATABASE_EXCEPTION = new ErrorCode("SE001", "DATABASE_EXCEPTION");
  
  // user service error code
  public static final ErrorCode LOGIN_INCORRECT_USERNAME_OR_PASSWORD = new ErrorCode("AE001", "LOGIN_INCORRECT_USERNAME_OR_PASSWORD");
  public static final ErrorCode LOGIN_ACCOUNT_DISABLED = new ErrorCode("AE002", "LOGIN_ACCOUNT_DISABLED");
  public static final ErrorCode ACCOUNT_HAS_NOT_ENOUGH_AUTHORITY = new ErrorCode("AE003", "ACCOUNT_HAS_NOT_ENOUGH_AUTHORITY");
  public static final ErrorCode REGISTER_USERNAME_OCCUPIED = new ErrorCode("RE001", "REGISTER_USERNAME_OCCUPIED");
  public static final ErrorCode USER_NOT_FOUND = new ErrorCode("US001", "USER_NOT_FOUND");
  public static final ErrorCode USER_IN_ILLEGAL_STATUS = new ErrorCode("US002", "USER_IN_ILLEGAL_STATUS");
  public static final ErrorCode UPDATE_PASSWORD_INCORRECT_PASSWORD = new ErrorCode("US003", "UPDATE_PASSWORD_INCORRECT_PASSWORD");
  
  // notification service error code
  public static final ErrorCode NOTIFCATION_ILLEGAL_STATUS = new ErrorCode("NE001", "NOTIFCATION_ILLEGAL_STATUS");
  public static final ErrorCode NOTIFY_USER_NOT_EXIST = new ErrorCode("NE002", "NOTIFY_USER_NOT_EXIST");
  public static final ErrorCode DELETE_MARKED_NOTIFICATION = new ErrorCode("NE003", "DELETE_MARKED_NOTIFICATION");
  
  // collection service error code
  public static final ErrorCode COLLECTION_ILLEGAL_STATUS = new ErrorCode("CE001", "COLLECTION_ILLEGAL_STATUS");
}
