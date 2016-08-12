package com.elminster.grs.giantbomb.service;

import com.elminster.grs.giantbomb.ds.GiantBombCompany;
import com.elminster.grs.giantbomb.ds.GiantBombGame;
import com.elminster.grs.giantbomb.ds.GiantBombGenre;
import com.elminster.grs.giantbomb.ds.GiantBombPlatform;
import com.elminster.grs.giantbomb.ds.GiantBombTheme;
import com.elminster.grs.giantbomb.ds.GiantBombVideo;

/**
 * The service to collect the game information from gaintgame.
 * 
 * @author jgu
 * @version 1.0
 */
public interface GiantBombCollectService {

  public void collectDetailGameInfo(CollectConf conf, GiantBombGame game) throws GiantBombCollectException;
  
  public void collectDetailPlatformInfo(CollectConf conf, GiantBombPlatform platform) throws GiantBombCollectException;
  
  public void collectDetailGenreInfo(CollectConf conf, GiantBombGenre genre) throws GiantBombCollectException;
  
  public void collectDetailThemeInfo(CollectConf conf, GiantBombTheme theme) throws GiantBombCollectException;
  
  public void collectDetailCompanyInfo(CollectConf conf, GiantBombCompany company) throws GiantBombCollectException;
  
  public void collectDetailVideoInfo(CollectConf conf, GiantBombVideo video) throws GiantBombCollectException;
}
