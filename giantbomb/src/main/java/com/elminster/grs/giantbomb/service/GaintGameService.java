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

  public GiantBombGame saveGame(GiantBombGame game);
  
  public Set<GiantBombPlatform> savePlatforms(Set<GiantBombPlatform> platforms);
  
  public GiantBombPlatform savePlatform(GiantBombPlatform platform);
  
  public Set<GiantBombCompany> savePublishers(Set<GiantBombCompany> publishers);

  public GiantBombCompany savePublisher(GiantBombCompany publisher);
  
  public Set<GiantBombTheme> saveThemes(Set<GiantBombTheme> themes);

  public GiantBombTheme saveTheme(GiantBombTheme theme);
  
  public Set<GiantBombGenre> saveGenres(Set<GiantBombGenre> genres);

  public GiantBombGenre saveGenre(GiantBombGenre genre);
  
  public Set<GiantBombCompany> saveDevelopers(Set<GiantBombCompany> developers);
  
  public GiantBombCompany saveDeveloper(GiantBombCompany developer);

  public Set<GiantBombVideo> saveVideos(Set<GiantBombVideo> videos);

  public GiantBombVideo saveVideo(GiantBombVideo video);
  
  //// ==================================================================  /////
  public Set<GiantBombGame> findGamesByStatus(GiantBombGameStatus status);

  public void updateStatus(GiantBombGame game, GiantBombGameStatus detailInfoCrawled);
}
