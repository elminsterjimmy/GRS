package com.elminster.grs.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.elminster.spring.security.domain.User;
import com.elminster.spring.security.model.UserDetailsImpl;
import com.elminster.spring.web.controller.BaseController;

public class BackendController extends BaseController {

  protected static final Log logger = LogFactory.getLog(BackendController.class);
  
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
