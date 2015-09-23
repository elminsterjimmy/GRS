package com.elminster.grs.web.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elminster.grs.web.security.model.TokenUserDetails;

/**
 * The user details service implementation.
 * 
 * @author jgu
 * @version 1.0
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

  /** the logger. */
  private static final Log logger = LogFactory.getLog(UserDetailServiceImpl.class);

  /**
   * {@inheritDoc}
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    logger.debug("loadUserByUsername(" + username + ")");

    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority("User"));
    
    UserDetails userDetails = new TokenUserDetails(username, "password", authorities);

    return userDetails;
  }

}
