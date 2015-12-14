package com.elminster.grs.giantbomb.service;

import java.util.Set;

import com.elminster.grs.giantbomb.ds.SingleCompany;
import com.elminster.grs.giantbomb.ds.SingleGame;
import com.elminster.grs.giantbomb.ds.SingleGenre;
import com.elminster.grs.giantbomb.ds.SinglePlatform;
import com.elminster.grs.giantbomb.ds.SingleTheme;

public interface GaintGameService {

  public void saveGame(SingleGame game);
  
  public void savePlatforms(Set<SinglePlatform> platforms);
  
  public void savePublishers(Set<SingleCompany> publishers);
  
  public void saveThemes(Set<SingleTheme> themes);
  
  public void saveGenres(Set<SingleGenre> genres);
  
  public void saveDevelop(Set<SingleCompany> developers);
}
