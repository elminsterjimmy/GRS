package com.elminster.grs.web.helper;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.elminster.common.exception.BaseException;
import com.elminster.grs.web.response.JsonResponse;
import com.elminster.grs.web.response.ResponseError;

public class ResponseErrorHelper {
  
  public static final ResponseErrorHelper INSTANCE = new ResponseErrorHelper();
  
  private ResponseErrorHelper() {}

  public JsonResponse generateErrorJsonResponse(Exception e) {
    JsonResponse jsonResponse = new JsonResponse();
    jsonResponse.setStatus(JsonResponse.STATUS_ERROR);
    jsonResponse.addError(generateResponseError(e));
    return jsonResponse;
  }
  
  public ResponseError generateResponseError(Exception e) {
    ResponseError error = new ResponseError();
    if (e instanceof BaseException) {
      error.setErrorCode(((BaseException)e).getExceptionCode().getCode());
    } else {
      error.setErrorCode(BaseException.UNKNOWN_CODE.getCode());
    }
    error.setErrorMessage(e.getMessage());
    error.setLocalizedErrorMessage(e.getLocalizedMessage());
    return error;
  }

  public ResponseError generateResponseError(FieldError e) {
    ResponseError error = new ResponseError();
    error.setErrorCode(e.getCode());
    error.setErrorMessage(e.getDefaultMessage());
    error.setLocalizedErrorMessage(e.getDefaultMessage());
    return error;
  }

  public ResponseError generateResponseError(ObjectError e) {
    ResponseError error = new ResponseError();
    error.setErrorCode(e.getCode());
    error.setErrorMessage(e.getDefaultMessage());
    error.setLocalizedErrorMessage(e.getDefaultMessage());
    return error;
  }
}
