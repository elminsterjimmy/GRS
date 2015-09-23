package com.elminster.grs.web.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.elminster.grs.web.security.service.TokenAuthenticationService;

/**
 * The filter that uses token to retrieve the user info.
 * 
 * @author jgu
 * @version 1.0
 */
@Component
public class StatelessAuthenticationFilter implements Filter {
  
  /** the token authentication service. */
  private final TokenAuthenticationService tokenAuthenticationService;
  
  /**
   * Constructor.
   * @param tokenAuthenticationService
   */
  @Autowired
  public StatelessAuthenticationFilter(TokenAuthenticationService tokenAuthenticationService) {
    this.tokenAuthenticationService = tokenAuthenticationService;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
      ServletException {
    SecurityContextHolder.getContext().setAuthentication(
        tokenAuthenticationService.getAuthenticationFromRequest((HttpServletRequest) request));
    chain.doFilter(request, response); // always continue
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void destroy() {
  }

}
