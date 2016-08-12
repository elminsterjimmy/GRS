package com.elminster.grs.giantbomb.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elminster.grs.giantbomb.dao.GiantBombPlatformDao;
import com.elminster.grs.giantbomb.ds.GiantBombPlatform;
import com.elminster.grs.giantbomb.ds.GiantBombStatus;
import com.elminster.grs.giantbomb.service.GiantPlatformService;

@Service
public class GiantPlatformServiceImpl implements GiantPlatformService {
  
  @Autowired
  GiantBombPlatformDao platformDao;
  
  @Autowired
  private EntityManager em;

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

  @Override
  public Set<GiantBombPlatform> findPlatformsByStatus(GiantBombStatus status) {
    return platformDao.findByStatus(status);
  }

  @Override
  public void updateStatus(GiantBombPlatform platform, GiantBombStatus status) {
    platform.setStatus(status);
    platformDao.save(platform);
  }

}
