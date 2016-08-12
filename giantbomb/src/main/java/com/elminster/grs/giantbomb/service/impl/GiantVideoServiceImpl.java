package com.elminster.grs.giantbomb.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elminster.grs.giantbomb.dao.GiantBombVideoDao;
import com.elminster.grs.giantbomb.ds.GiantBombStatus;
import com.elminster.grs.giantbomb.ds.GiantBombVideo;
import com.elminster.grs.giantbomb.service.GiantVideoService;

@Service
public class GiantVideoServiceImpl implements GiantVideoService {
  
  @Autowired
  GiantBombVideoDao VideoDao;
  
  @Autowired
  private EntityManager em;

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public Set<GiantBombVideo> saveVideos(final Set<GiantBombVideo> Videos) {
    Set<GiantBombVideo> managed = null;
    if (null != Videos) {
      managed = new HashSet<>();
      for (GiantBombVideo pl : Videos) {
        managed.add(saveVideo(pl));
      }
    }
    return managed;
  }
  
  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public GiantBombVideo saveVideo(final GiantBombVideo Video) {
    GiantBombVideo managed = null;
    if (null != Video) {
      GiantBombVideo exist = VideoDao.findByGamebombId(Video.getGamebombId());
      if (null != exist) {
        managed = updateVideo(exist, Video);
      } else {
        managed = persistVideo(Video);
      }
    }
    return managed;
  }
  
  private GiantBombVideo persistVideo(final GiantBombVideo Video) {
    em.persist(Video);
    return Video;
  }

  private GiantBombVideo updateVideo(final GiantBombVideo exist, final GiantBombVideo Video) {
    exist.fulfill(Video);
    return em.merge(exist);
  }

  @Override
  public GiantBombVideo findVideoByGamebombId(int gbId) {
    return VideoDao.findByGamebombId(gbId);
  }

  @Override
  public Set<GiantBombVideo> findVideosByStatus(GiantBombStatus status) {
    return VideoDao.findByStatus(status);
  }

  @Override
  public void updateStatus(GiantBombVideo Video, GiantBombStatus status) {
    Video.setStatus(status);
    VideoDao.save(Video);
  }

}
