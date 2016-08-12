package com.elminster.grs.giantbomb.service.impl;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elminster.grs.giantbomb.dao.GiantBombGameDao;
import com.elminster.grs.giantbomb.dao.GiantBombImageDao;
import com.elminster.grs.giantbomb.ds.GiantBombGame;
import com.elminster.grs.giantbomb.ds.GiantBombImage;
import com.elminster.grs.giantbomb.ds.GiantBombStatus;
import com.elminster.grs.giantbomb.service.GiantCompanyService;
import com.elminster.grs.giantbomb.service.GiantGameService;
import com.elminster.grs.giantbomb.service.GiantGenreService;
import com.elminster.grs.giantbomb.service.GiantPlatformService;
import com.elminster.grs.giantbomb.service.GiantThemeService;
import com.elminster.grs.giantbomb.service.GiantVideoService;

@Service
public class GiantGameServiceImpl implements GiantGameService {
  
  private static final Log logger = LogFactory.getLog(GiantGameServiceImpl.class);

  @Autowired
  GiantBombGameDao gameDao;
  @Autowired
  GiantBombImageDao imageDao;
  @Autowired
  GiantPlatformService platformService;
  @Autowired
  GiantCompanyService companyService;
  @Autowired
  GiantGenreService genreService;
  @Autowired
  GiantThemeService themeService;
  @Autowired
  GiantVideoService videoService;
  
  
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
    game.setDevelopers(companyService.saveCompanys(game.getDevelopers()));
    game.setGenres(genreService.saveGenres(game.getGenres()));
    game.setThemes(themeService.saveThemes(game.getThemes()));
    game.setPublishers(companyService.saveCompanys(game.getPublishers()));
    game.setPlatforms(platformService.savePlatforms(game.getPlatforms()));
    game.setVideos(videoService.saveVideos(game.getVideos()));
    game.setImages(saveImages(game.getImages()));
    em.persist(game);
    return game;
  }

  private GiantBombGame updateGame(final GiantBombGame exist, final GiantBombGame game) {
    exist.fulfill(game);
    exist.setDevelopers(companyService.saveCompanys(game.getDevelopers()));
    exist.setGenres(genreService.saveGenres(game.getGenres()));
    exist.setThemes(themeService.saveThemes(game.getThemes()));
    exist.setPublishers(companyService.saveCompanys(game.getPublishers()));
    exist.setPlatforms(platformService.savePlatforms(game.getPlatforms()));
    exist.setVideos(videoService.saveVideos(game.getVideos()));
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
  
  @Override
  public Set<GiantBombGame> findGamesByStatus(GiantBombStatus status) {
    return gameDao.findByStatus(status);
  }

  @Transactional
  @Override
  public void updateStatus(GiantBombGame game, GiantBombStatus status) {
    game.setStatus(status);
    gameDao.save(game);
  }
}
