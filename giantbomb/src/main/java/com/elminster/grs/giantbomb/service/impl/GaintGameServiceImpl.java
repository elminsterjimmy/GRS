package com.elminster.grs.giantbomb.service.impl;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import com.elminster.grs.giantbomb.ds.SingleCompany;
import com.elminster.grs.giantbomb.ds.SingleGame;
import com.elminster.grs.giantbomb.ds.SingleGenre;
import com.elminster.grs.giantbomb.ds.SinglePlatform;
import com.elminster.grs.giantbomb.ds.SingleTheme;
import com.elminster.grs.giantbomb.ds.SingleVideo;
import com.elminster.grs.giantbomb.service.GaintGameService;

@Service
@Transactional
public class GaintGameServiceImpl implements GaintGameService {
  
  @PersistenceContext
  private EntityManager em;

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
  public void saveGame(SingleGame game) {
    SingleGame exist = gameDao.findByGamebombId(game.getGamebombId());
    if (null != exist) {
      game.setInternalId(exist.getInternalId());
      game.setImage(exist.getImage());
      if (null != exist.getImages()) {
        game.setImages(exist.getImages());
      }
    }
    
    Set<SingleCompany> developers = game.getDevelopers();
    saveDevelop(developers);
    Set<SingleGenre> genres = game.getGenres();
    saveGenres(genres);
    Set<SingleTheme> themes = game.getThemes();
    saveThemes(themes);
    Set<SingleCompany> publishers = game.getPublishers();
    savePublishers(publishers);
    Set<SinglePlatform> platforms = game.getPlatforms();
    savePlatforms(platforms);
    Set<SingleVideo> videos = game.getVideos();
    saveVideos(videos);
    gameDao.save(game);
  }
  
  private void saveVideos(Set<SingleVideo> videos) {
    if (null != videos) {
      for (SingleVideo pl : videos) {
        SingleVideo exist = videoDao.findByGamebombId(pl.getGamebombId());
        if (null != exist) {
          pl.setInternalId(exist.getInternalId());
        } else {
          videoDao.save(pl);
        }
      }
    }
  }

  @Override
  public void savePlatforms(Set<SinglePlatform> platforms) {
    if (null != platforms) {
      for (SinglePlatform pl : platforms) {
        SinglePlatform exist = platformDao.findByGamebombId(pl.getGamebombId());
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
  public void savePublishers(Set<SingleCompany> publishers) {
    if (null != publishers) {
      for (SingleCompany pl : publishers) {
        SingleCompany exist = companyDao.findByGamebombId(pl.getGamebombId());
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
  public void saveThemes(Set<SingleTheme> themes) {
    if (null != themes) {
      for (SingleTheme pl : themes) {
        SingleTheme exist = themeDao.findByGamebombId(pl.getGamebombId());
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
  public void saveGenres(Set<SingleGenre> genres) {
    if (null != genres) {
      for (SingleGenre pl : genres) {
        SingleGenre exist = genreDao.findByGamebombId(pl.getGamebombId());
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
  public void saveDevelop(Set<SingleCompany> developers) {
    if (null != developers) {
      for (SingleCompany pl : developers) {
        SingleCompany exist = companyDao.findByGamebombId(pl.getGamebombId());
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
