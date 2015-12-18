package com.elminster.grs.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

import com.elminster.grs.web.entrypoint.Http401UnauthorizedEntryPoint;
import com.elminster.spring.security.filter.StatelessAuthenticationFilter;
import com.elminster.spring.security.service.UserDetailsServiceImpl;
import com.elminster.web.commons.filter.SimpleCORSFilter;

/**
 * Security configuration.
 * 
 * @author jgu
 * @version 1.0
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  
  /**
   * The calls that work on no permission.
   */
  //@formatter:off
  private static final String[] NO_PERMISSION_CALLS = {
    "/v1.0/user/login",
    "/v1.0/user/register",
    "/v1.0/user/usernameOccupied",
    "/v1.0/user/test"
  };
  // @formatter:on
  
  @Autowired
  private StatelessAuthenticationFilter statelessAuthenticationFilter;
  @Autowired
  private Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint;
  private UserDetailsService userDetailService = new UserDetailsServiceImpl();

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http.csrf().disable();
    //http.addFilterAfter(new CsrfTokenFilter(), CsrfFilter.class);
    http.addFilterBefore(new SimpleCORSFilter(), CsrfFilter.class);
    http.addFilterBefore(statelessAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.exceptionHandling().authenticationEntryPoint(http401UnauthorizedEntryPoint);
    http.authorizeRequests()
      .antMatchers(NO_PERMISSION_CALLS).permitAll()
      .anyRequest().authenticated();
    // @formatter:on
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailService);
  }

  @Override
  public UserDetailsService userDetailsServiceBean() {
    return userDetailService;
  }
}