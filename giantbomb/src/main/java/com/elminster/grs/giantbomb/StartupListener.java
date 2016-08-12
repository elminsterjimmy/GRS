package com.elminster.grs.giantbomb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.elminster.common.util.DateUtil;
import com.elminster.grs.giantbomb.config.Config;
import com.elminster.grs.giantbomb.service.GameCollectConf;
import com.elminster.grs.giantbomb.service.GiantGameCollectService;
import com.elminster.grs.giantbomb.util.ApiCounter;
import com.elminster.grs.giantbomb.util.ApiCounterManager;

/**
 * The Application startup listener.
 * 
 * @author jinggu
 * @version 1.0
 */
@Component
public class StartupListener implements ApplicationListener<ApplicationReadyEvent> {

  private static final String API_KEY = Config.INSTANCE.getStringProperty("API_KEY");
  private static final ApiCounter INTERNAL_GAMES_API_CONTER = 
      ApiCounterManager.INSTANCE.getApiCounter("games", API_KEY, 180, DateUtil.HOUR);
  private static final ApiCounter INTERNAL_GAME_API_COUNTER = 
      ApiCounterManager.INSTANCE.getApiCounter("game", API_KEY, 180, DateUtil.HOUR);

  @Autowired
  private GiantGameCollectService collectService;

  /**
   * Run the collect job on startup.
   */
  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    GameCollectConf basicGameCollectConf = new GameCollectConf();
    basicGameCollectConf.setApiCounter(INTERNAL_GAMES_API_CONTER);
    collectService.collectBasicGameInfo(basicGameCollectConf);
    GameCollectConf detailsGameCollectConf = new GameCollectConf();
    detailsGameCollectConf.setApiCounter(INTERNAL_GAME_API_COUNTER);
    collectService.collectDetailGameInfo(detailsGameCollectConf);
  }

}
