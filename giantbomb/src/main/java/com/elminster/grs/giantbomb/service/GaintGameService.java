package com.elminster.grs.giantbomb.service;

import java.util.Set;

import com.elminster.grs.giantbomb.ds.GiantBombCompany;
import com.elminster.grs.giantbomb.ds.GiantBombGame;
import com.elminster.grs.giantbomb.ds.GiantBombGame.GiantBombGameStatus;
import com.elminster.grs.giantbomb.ds.GiantBombGenre;
import com.elminster.grs.giantbomb.ds.GiantBombPlatform;
import com.elminster.grs.giantbomb.ds.GiantBombTheme;
import com.elminster.grs.giantbomb.ds.GiantBombVideo;

/**
 * The Service to store the game information that crawled from gaintgame into the database.
 * 
 * @author jgu
 * @version 1.0
 */
public interface GaintGameService {

  public void saveGame(GiantBombGame game);
  
  public void savePlatforms(Set<GiantBombPlatform> platforms);
  
  public void savePublishers(Set<GiantBombCompany> publishers);
  
  public void saveThemes(Set<GiantBombTheme> themes);
  
  public void saveGenres(Set<GiantBombGenre> genres);
  
  public void saveDevelop(Set<GiantBombCompany> developers);
  
  public void saveVideos(Set<GiantBombVideo> videos);
  
  public Set<GiantBombGame> findGamesByStatus(GiantBombGameStatus status);
}
