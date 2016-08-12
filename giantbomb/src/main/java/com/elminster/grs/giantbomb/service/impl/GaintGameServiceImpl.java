package com.elminster.grs.giantbomb.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.elminster.grs.giantbomb.ds.GiantBombGame.GiantBombGameStatus;
import com.elminster.grs.giantbomb.ds.GiantBombGenre;
import com.elminster.grs.giantbomb.ds.GiantBombImage;
import com.elminster.grs.giantbomb.ds.GiantBombPlatform;
import com.elminster.grs.giantbomb.ds.GiantBombTheme;
import com.elminster.grs.giantbomb.ds.GiantBombVideo;
import com.elminster.grs.giantbomb.service.GaintGameService;

@Service
public class GaintGameServiceImpl implements GaintGameService {
  
  private static final Log logger = LogFactory.getLog(GaintGameServiceImpl.class);

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
  
  @Autowired
  private EntityManager em;
  
  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  synchronized public GiantBombGame saveGame(final GiantBombGame game) {
    GiantBombGame exist = gameDao.findByGamebombId(game.getGamebombId());
    GiantBombGame managed = null;
    if (null != exist) {
      managed = updateGame(exist, game);
    } else {
      managed = persistGame(game);
    }
    return managed;
  }

  private GiantBombGame persistGame(final GiantBombGame game) {
    game.setDevelopers(saveDevelopers(game.getDevelopers()));
    game.setGenres(saveGenres(game.getGenres()));
    game.setThemes(saveThemes(game.getThemes()));
    game.setPublishers(savePublishers(game.getPublishers()));
    game.setPlatforms(savePlatforms(game.getPlatforms()));
    game.setVideos(saveVideos(game.getVideos()));
    game.setImages(saveImages(game.getImages()));
    em.persist(game);
    return game;
  }

  private GiantBombGame updateGame(final GiantBombGame exist, final GiantBombGame game) {
    exist.fulfill(game);
    exist.setDevelopers(saveDevelopers(game.getDevelopers()));
    exist.setGenres(saveGenres(game.getGenres()));
    exist.setThemes(saveThemes(game.getThemes()));
    exist.setPublishers(savePublishers(game.getPublishers()));
    exist.setPlatforms(savePlatforms(game.getPlatforms()));
    exist.setVideos(saveVideos(game.getVideos()));
    exist.setImages(saveImages(game.getImages()));
    return em.merge(exist);
  }

  private Set<GiantBombImage> saveImages(Set<GiantBombImage> images) {
    if (null != images && !images.isEmpty()) {
      imageDao.delete(images);
      imageDao.save(images);
    }
    return images;
  }
  
  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public Set<GiantBombVideo> saveVideos(final Set<GiantBombVideo> videos) {
    Set<GiantBombVideo> managed = null;
    if (null != videos) {
      managed = new HashSet<>();
      for (GiantBombVideo video : videos) {
        managed.add(saveVideo(video));
      }
    }
    return managed;
  }
  
  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public GiantBombVideo saveVideo(final GiantBombVideo video) {
    GiantBombVideo managed = null;
    if (null != video) {
      GiantBombVideo exist = videoDao.findByGamebombId(video.getGamebombId());
      if (null != exist) {
        managed = updateVideo(exist, video);
      } else {
        managed = persistVideo(video);
      }
    }
    return managed;
  }
  
  private GiantBombVideo persistVideo(final GiantBombVideo video) {
    em.persist(video);
    return video;
  }

  private GiantBombVideo updateVideo(final GiantBombVideo exist, final GiantBombVideo video) {
    exist.fulfill(video);
    return em.merge(exist);
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public Set<GiantBombPlatform> savePlatforms(final Set<GiantBombPlatform> platforms) {
    Set<GiantBombPlatform> managed = null;
    if (null != platforms) {
      managed = new HashSet<>();
      for (GiantBombPlatform pl : platforms) {
        managed.add(savePlatform(pl));
      }
    }
    return managed;
  }
  
  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public GiantBombPlatform savePlatform(final GiantBombPlatform platform) {
    GiantBombPlatform managed = null;
    if (null != platform) {
      GiantBombPlatform exist = platformDao.findByGamebombId(platform.getGamebombId());
      if (null != exist) {
        managed = updatePlatform(exist, platform);
      } else {
        managed = persistPlatform(platform);
      }
    }
    return managed;
  }
  
  private GiantBombPlatform persistPlatform(final GiantBombPlatform platform) {
    em.persist(platform);
    return platform;
  }

  private GiantBombPlatform updatePlatform(final GiantBombPlatform exist, final GiantBombPlatform platform) {
    exist.fulfill(platform);
    return em.merge(exist);
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public Set<GiantBombCompany> savePublishers(Set<GiantBombCompany> publishers) {
    Set<GiantBombCompany> managed = null;
    if (null != publishers) {
      managed = new HashSet<>();
      for (GiantBombCompany publisher : publishers) {
        managed.add(saveDeveloper(publisher));
      }
    }
    return managed;
  }
  
  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public GiantBombCompany savePublisher(final GiantBombCompany publisher) {
    GiantBombCompany managed = null;
    if (null != publisher) {
      GiantBombCompany exist = companyDao.findByGamebombId(publisher.getGamebombId());
      if (null != exist) {
        managed = updatePublisher(exist, publisher);
      } else {
        managed = persistPublisher(publisher);
      }
    }
    return managed;
  }

  private GiantBombCompany updatePublisher(final GiantBombCompany exist, final GiantBombCompany publisher) {
    exist.fulfill(publisher);
    return em.merge(exist);
  }

  private GiantBombCompany persistPublisher(final GiantBombCompany publisher) {
    em.persist(publisher);
    return publisher;
  }


  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public Set<GiantBombTheme> saveThemes(Set<GiantBombTheme> themes) {
    Set<GiantBombTheme> managed = null;
    if (null != themes) {
      managed = new HashSet<>();
      for (GiantBombTheme theme : themes) {
        managed.add(saveTheme(theme));
      }
    }
    return managed;
  }
  
  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public GiantBombTheme saveTheme(final GiantBombTheme theme) {
    GiantBombTheme managed = null;
    if (null != theme) {
      GiantBombTheme exist = themeDao.findByGamebombId(theme.getGamebombId());
      if (null != exist) {
        managed = updateTheme(exist, theme);
      } else {
        managed = persistTheme(theme);
      }
    }
    return managed;
  }

  private GiantBombTheme persistTheme(final GiantBombTheme theme) {
    em.persist(theme);
    return theme;
  }

  private GiantBombTheme updateTheme(final GiantBombTheme exist, final GiantBombTheme theme) {
    exist.fulfill(theme);
    return em.merge(exist);
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public Set<GiantBombGenre> saveGenres(Set<GiantBombGenre> genres) {
    Set<GiantBombGenre> managed = null;
    if (null != genres) {
      managed = new HashSet<>();
      for (GiantBombGenre genre : genres) {
        managed.add(saveGenre(genre));
      }
    }
    return managed;
  }
  
  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public GiantBombGenre saveGenre(final GiantBombGenre genre) {
    GiantBombGenre managed = null;
    if (null != genre) {
      GiantBombGenre exist = genreDao.findByGamebombId(genre.getGamebombId());
      if (null != exist) {
        managed = updateGenre(exist, genre);
      } else {
        managed = persistGenre(genre);
      }
    }
    return managed;
  }

  private GiantBombGenre persistGenre(final GiantBombGenre genre) {
    em.persist(genre);
    return genre;
  }

  private GiantBombGenre updateGenre(final GiantBombGenre exist, final GiantBombGenre genre) {
    exist.fulfill(genre);
    return em.merge(exist);
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public Set<GiantBombCompany> saveDevelopers(Set<GiantBombCompany> developers) {
    Set<GiantBombCompany> managed = null;
    if (null != developers) {
      managed = new HashSet<>();
      for (GiantBombCompany developer : developers) {
        managed.add(saveDeveloper(developer));
      }
    }
    return managed;
  }
  
  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public GiantBombCompany saveDeveloper(GiantBombCompany developer) {
    GiantBombCompany managed = null;
    if (null != developer) {
      GiantBombCompany exist = companyDao.findByGamebombId(developer.getGamebombId());
      if (null != exist) {
        managed = updateDeveloper(exist, developer);
      } else {
        managed = persistDeveloper(developer);
      }
    }
    return managed;
  }

  private GiantBombCompany updateDeveloper(final GiantBombCompany exist, final GiantBombCompany developer) {
    exist.fulfill(developer);
    return em.merge(exist);
  }

  private GiantBombCompany persistDeveloper(final GiantBombCompany developer) {
    em.persist(developer);
    return developer;
  }

  @Override
  public Set<GiantBombGame> findGamesByStatus(GiantBombGameStatus status) {
    return gameDao.findByStatus(status);
  }

  @Transactional
  @Override
  public void updateStatus(GiantBombGame game, GiantBombGameStatus status) {
    game.setStatus(status);
    gameDao.save(game);
  }
}
