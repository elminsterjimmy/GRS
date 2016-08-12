package com.elminster.grs.giantbomb.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elminster.grs.giantbomb.dao.GiantBombThemeDao;
import com.elminster.grs.giantbomb.ds.GiantBombTheme;
import com.elminster.grs.giantbomb.ds.GiantBombStatus;
import com.elminster.grs.giantbomb.service.GiantThemeService;

@Service
public class GiantThemeServiceImpl implements GiantThemeService {
  
  @Autowired
  GiantBombThemeDao ThemeDao;
  
  @Autowired
  private EntityManager em;

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public Set<GiantBombTheme> saveThemes(final Set<GiantBombTheme> Themes) {
    Set<GiantBombTheme> managed = null;
    if (null != Themes) {
      managed = new HashSet<>();
      for (GiantBombTheme pl : Themes) {
        managed.add(saveTheme(pl));
      }
    }
    return managed;
  }
  
  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public GiantBombTheme saveTheme(final GiantBombTheme Theme) {
    GiantBombTheme managed = null;
    if (null != Theme) {
      GiantBombTheme exist = ThemeDao.findByGamebombId(Theme.getGamebombId());
      if (null != exist) {
        managed = updateTheme(exist, Theme);
      } else {
        managed = persistTheme(Theme);
      }
    }
    return managed;
  }
  
  private GiantBombTheme persistTheme(final GiantBombTheme Theme) {
    em.persist(Theme);
    return Theme;
  }

  private GiantBombTheme updateTheme(final GiantBombTheme exist, final GiantBombTheme Theme) {
    exist.fulfill(Theme);
    return em.merge(exist);
  }

  @Override
  public Set<GiantBombTheme> findThemesByStatus(GiantBombStatus status) {
    return ThemeDao.findByStatus(status);
  }

  @Override
  public void updateStatus(GiantBombTheme Theme, GiantBombStatus status) {
    Theme.setStatus(status);
    ThemeDao.save(Theme);
  }
}
