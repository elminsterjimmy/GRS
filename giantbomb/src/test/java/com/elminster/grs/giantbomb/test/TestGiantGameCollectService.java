package com.elminster.grs.giantbomb.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.elminster.grs.giantbomb.Application;
import com.elminster.grs.giantbomb.service.GiantGameCollectService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestGiantGameCollectService {

  @Autowired
  GiantGameCollectService service;

  @Test
  public void testGiantGameCollectService() {
    service.collectGameInfo();
  }

}
