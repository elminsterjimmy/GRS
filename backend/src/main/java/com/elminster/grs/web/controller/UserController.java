package com.elminster.grs.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The user controller.
 * 
 * @author jgu
 * @version 1.0
 */
@RestController
public class UserController {

  @RequestMapping(value = "/user/test", method = RequestMethod.GET)
  public void test() {
    
  }
}
