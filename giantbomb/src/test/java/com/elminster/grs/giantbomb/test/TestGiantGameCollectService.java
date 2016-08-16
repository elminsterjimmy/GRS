package com.elminster.grs.giantbomb.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.elminster.common.pool.ThreadPoolJVMShutdownHook;
import com.elminster.common.util.DateUtil;
import com.elminster.grs.giantbomb.GiantBombCrawlerApplication;
import com.elminster.grs.giantbomb.service.GiantBombCollectService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GiantBombCrawlerApplication.class)
public class TestGiantGameCollectService {

  @Autowired
  GiantBombCollectService service;

  //@Test
  public void testCollectBasicGameInfo() {
//    service.collectBasicGameInfo();
  }
  
  @Test
  public void testCollectDetailGameInfo() {
//    service.collectDetailGameInfo();
//    try {
//      Thread.sleep(DateUtil.HOUR);
//    } catch (InterruptedException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//    Runtime.getRuntime().addShutdownHook(new ThreadPoolJVMShutdownHook(12, TimeUnit.HOURS));
  }
}
