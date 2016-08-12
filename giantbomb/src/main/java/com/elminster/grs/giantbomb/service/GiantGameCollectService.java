package com.elminster.grs.giantbomb.service;

import com.elminster.grs.giantbomb.ds.GiantBombGame;

/**
 * The service to collect the game information from gaintgame.
 * 
 * @author jgu
 * @version 1.0
 */
public interface GiantGameCollectService {

  public void collectBasicGameInfo(GameCollectConf conf);
  
  public void collectDetailGameInfo(GameCollectConf conf);
  
  public void collectDetailGameInfo(GameCollectConf conf, GiantBombGame game) throws GameCollectException;
}
