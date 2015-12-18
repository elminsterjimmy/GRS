package com.elminster.grs.giantbomb.service.impl;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elminster.grs.giantbomb.dao.GiantBombCompanyDao;
import com.elminster.grs.giantbomb.dao.GiantBombGameDao;
import com.elminster.grs.giantbomb.dao.GiantBombGenreDao;
import com.elminster.grs.giantbomb.dao.GiantBombImageDao;
import com.elminster.grs.giantbomb.dao.GiantBombPlatformDao;
import com.elminster.grs.giantbomb.dao.GiantBombThemeDao;
import com.elminster.grs.giantbomb.dao.GiantBombVideoDao;
import com.elminster.grs.giantbomb.ds.GiantBombCompany;
import com.elminster.grs.giantbomb.ds.GiantBombGame;
import com.elminster.grs.giantbomb.ds.GiantBombGenre;
import com.elminster.grs.giantbomb.ds.GiantBombPlatform;
import com.elminster.grs.giantbomb.ds.GiantBombTheme;
import com.elminster.grs.giantbomb.ds.GiantBombVideo;
import com.elminster.grs.giantbomb.service.GaintGameService;

@Service
@Transactional
public class GaintGameServiceImpl implements GaintGameService {
  
  @Autowired
  GiantBombGameDao gameDao;
  @Autowired
  GiantBombPlatformDao platformDao;
  @Autowired
  GiantBombCompanyDao companyDao;
  @Autowired
  GiantBombGenreDao genreDao;
  @Autowired
  GiantBombThemeDao themeDao;
  @Autowired
  GiantBombImageDao imageDao;
  @Autowired
  GiantBombVideoDao videoDao;
  
  @Override
  public void saveGame(GiantBombGame game) {
    GiantBombGame exist = gameDao.findByGamebombId(game.getGamebombId());
    if (null != exist) {
      game.setInternalId(exist.getInternalId());
      game.setImage(exist.getImage());
      if (null != exist.getImages()) {
        game.setImages(exist.getImages());
      }
    }
    
    Set<GiantBombCompany> developers = game.getDevelopers();
    saveDevelop(developers);
    Set<GiantBombGenre> genres = game.getGenres();
    saveGenres(genres);
    Set<GiantBombTheme> themes = game.getThemes();
    saveThemes(themes);
    Set<GiantBombCompany> publishers = game.getPublishers();
    savePublishers(publishers);
    Set<GiantBombPlatform> platforms = game.getPlatforms();
    savePlatforms(platforms);
    Set<GiantBombVideo> videos = game.getVideos();
    saveVideos(videos);
    gameDao.save(game);
  }
  
  private void saveVideos(Set<GiantBombVideo> videos) {
    if (null != videos) {
      for (GiantBombVideo pl : videos) {
        GiantBombVideo exist = videoDao.findByGamebombId(pl.getGamebombId());
        if (null != exist) {
          pl.setInternalId(exist.getInternalId());
        } else {
          videoDao.save(pl);
        }
      }
    }
  }

  @Override
  public void savePlatforms(Set<GiantBombPlatform> platforms) {
    if (null != platforms) {
      for (GiantBombPlatform pl : platforms) {
        GiantBombPlatform exist = platformDao.findByGamebombId(pl.getGamebombId());
        if (null != exist) {
          pl.setInternalId(exist.getInternalId());
          pl.setImage(exist.getImage());
        } else {
          platformDao.save(pl);
        }
      }
    }
  }

  @Override
  public void savePublishers(Set<GiantBombCompany> publishers) {
    if (null != publishers) {
      for (GiantBombCompany pl : publishers) {
        GiantBombCompany exist = companyDao.findByGamebombId(pl.getGamebombId());
        if (null != exist) {
          pl.setInternalId(exist.getInternalId());
          pl.setImage(exist.getImage());
        } else {
          companyDao.save(pl);
        }
      }
    }
  }

  @Override
  public void saveThemes(Set<GiantBombTheme> themes) {
    if (null != themes) {
      for (GiantBombTheme pl : themes) {
        GiantBombTheme exist = themeDao.findByGamebombId(pl.getGamebombId());
        if (null != exist) {
          pl.setInternalId(exist.getInternalId());
          pl.setImage(exist.getImage());
        } else {
          themeDao.save(pl);
        }
      }
    }
  }

  @Override
  public void saveGenres(Set<GiantBombGenre> genres) {
    if (null != genres) {
      for (GiantBombGenre pl : genres) {
        GiantBombGenre exist = genreDao.findByGamebombId(pl.getGamebombId());
        if (null != exist) {
          pl.setInternalId(exist.getInternalId());
          pl.setImage(exist.getImage());
        } else {
          genreDao.save(pl);
        }
      }
    }
  }

  @Override
  public void saveDevelop(Set<GiantBombCompany> developers) {
    if (null != developers) {
      for (GiantBombCompany pl : developers) {
        GiantBombCompany exist = companyDao.findByGamebombId(pl.getGamebombId());
        if (null != exist) {
          pl.setInternalId(exist.getInternalId());
          pl.setImage(exist.getImage());
        } else {
          companyDao.save(pl);
        }
      }
    }
  }
}
