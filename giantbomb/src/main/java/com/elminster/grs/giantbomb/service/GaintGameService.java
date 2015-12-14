package com.elminster.grs.giantbomb.service;

import java.util.Set;

import com.elminster.grs.giantbomb.ds.GiantBombCompany;
import com.elminster.grs.giantbomb.ds.GiantBombGame;
import com.elminster.grs.giantbomb.ds.GiantBombGenre;
import com.elminster.grs.giantbomb.ds.GiantBombPlatform;
import com.elminster.grs.giantbomb.ds.GiantBombTheme;

public interface GaintGameService {

  public void saveGame(GiantBombGame game);
  
  public void savePlatforms(Set<GiantBombPlatform> platforms);
  
  public void savePublishers(Set<GiantBombCompany> publishers);
  
  public void saveThemes(Set<GiantBombTheme> themes);
  
  public void saveGenres(Set<GiantBombGenre> genres);
  
  public void saveDevelop(Set<GiantBombCompany> developers);
}
