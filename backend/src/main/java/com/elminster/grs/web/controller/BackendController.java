package com.elminster.grs.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elminster.grs.web.helper.BindingResultHelper;
import com.elminster.spring.security.domain.User;
import com.elminster.spring.security.model.UserDetailsImpl;
import com.elminster.web.commons.response.JsonResponse;
import com.elminster.web.commons.response.JsonResponseBuilder;

public class BaseController {

  protected static final Log logger = LogFactory.getLog(BaseController.class);
  protected static JsonResponseBuilder jsonResponseBuilder = JsonResponseBuilder.INSTANCE;

  // handle validate exception
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public ResponseEntity<JsonResponse> handleValidateException(MethodArgumentNotValidException exception) {
    logger.error("Validate failed. Details: " + exception.getLocalizedMessage());
    return new ResponseEntity<JsonResponse>(BindingResultHelper.INSTANCE.buildErrorJsonResponse(exception
        .getBindingResult()), HttpStatus.BAD_REQUEST);
  }

  // handle uncaught exception
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity<JsonResponse> handleUncaughtException(Exception exception) {
    logger.error("Internet server error. Details: " + exception.getLocalizedMessage());
    return new ResponseEntity<JsonResponse>(jsonResponseBuilder.buildErrorJsonResponse(exception),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  /**
   * Get current user from authentication.
   * @return current user
   */
  protected User getCurrentAuthUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserDetailsImpl ud = (UserDetailsImpl) auth.getDetails();
    return ud.getUser();
  }
}
