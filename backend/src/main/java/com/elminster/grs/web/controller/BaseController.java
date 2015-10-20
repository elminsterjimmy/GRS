package com.elminster.grs.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elminster.grs.web.helper.BindingResultHelper;
import com.elminster.grs.web.helper.JsonResponseBuilder;
import com.elminster.grs.web.response.JsonResponse;

public class BaseController {

  protected static final Log logger = LogFactory.getLog(BaseController.class);

  // handle validate exception
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public ResponseEntity<JsonResponse> handleValidateException(MethodArgumentNotValidException exception) {
    if (logger.isDebugEnabled()) {
      logger.debug("Validate failed. Details: " + exception.getLocalizedMessage());
    }
    return new ResponseEntity<JsonResponse>(BindingResultHelper.INSTANCE.buildErrorJsonResponse(exception
        .getBindingResult()), HttpStatus.BAD_REQUEST);
  }

  // handle uncaught exception
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity<JsonResponse> handleUncaughtException(Exception exception) {
    if (logger.isDebugEnabled()) {
      logger.debug("Internet server error. Details: " + exception.getLocalizedMessage());
    }
    return new ResponseEntity<JsonResponse>(JsonResponseBuilder.INSTANCE.buildErrorJsonResponse(exception),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
