package com.elminster.grs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.bind.annotation.RestController;

import com.elminster.grs.web.entrypoint.Http401UnauthorizedEntryPoint;
import com.elminster.spring.security.filter.CsrfTokenFilter;
import com.elminster.spring.security.filter.StatelessAuthenticationFilter;

/**
 * The application.
 * 
 * @author jgu
 * @version 1.0
 */
@SpringBootApplication
@RestController
@ComponentScan({"com.elminster.spring.security", "com.elminster.grs"})
public class GSRBackendApplication {
  
  /**
   * The main.
   * 
   * @param args
   *          the arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(GSRBackendApplication.class, args);
  }

  /**
   * Security configuration.
   * 
   * @author jgu
   * @version 1.0
   */
  @Configuration
  @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
  protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private StatelessAuthenticationFilter statelessAuthenticationFilter;
    @Autowired
    private Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint;
    @Autowired
    private UserDetailsService userDetailsService;
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      // @formatter:off
      http.csrf().disable();
      http.addFilterAfter(new CsrfTokenFilter(), CsrfFilter.class);
      http.addFilterBefore(statelessAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      http.exceptionHandling().authenticationEntryPoint(http401UnauthorizedEntryPoint);
      http
        .authorizeRequests()
        .antMatchers("/v1/**", "/user/login").permitAll()
        .anyRequest().authenticated();
      // @formatter:on
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public UserDetailsService userDetailsServiceBean() {
        return userDetailsService;
    }
  }
}
