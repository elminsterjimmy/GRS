package com.elminster.grs.web.entrypoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * The 401 unauthorized entry point.
 * 
 * @author jgu
 * @version 1.0
 */
@Component
public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {
  /** the logger. */
  private static final Log logger = LogFactory.getLog(Http401UnauthorizedEntryPoint.class);

  /**
   * Always returns a 401 error code to the client.
   */
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2)
      throws IOException, ServletException {
    if (logger.isDebugEnabled()) {
      logger.debug("Pre-authenticated entry point called. Rejecting access");
    }
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
  }

}
