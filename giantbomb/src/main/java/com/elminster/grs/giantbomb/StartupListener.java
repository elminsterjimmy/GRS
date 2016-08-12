package com.elminster.grs.giantbomb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.elminster.grs.giantbomb.service.GiantBombAutoCollectService;

/**
 * The Application startup listener.
 * 
 * @author jinggu
 * @version 1.0
 */
@Component
public class StartupListener implements ApplicationListener<ApplicationReadyEvent> {

  @Autowired
  private GiantBombAutoCollectService collectService;

  /**
   * Run the collect job on startup.
   */
  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    collectService.autoRun();
  }

}
