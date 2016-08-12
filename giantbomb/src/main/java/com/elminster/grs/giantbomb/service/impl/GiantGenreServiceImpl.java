package com.elminster.grs.giantbomb.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elminster.grs.giantbomb.dao.GiantBombGenreDao;
import com.elminster.grs.giantbomb.ds.GiantBombGenre;
import com.elminster.grs.giantbomb.ds.GiantBombStatus;
import com.elminster.grs.giantbomb.service.GiantGenreService;

@Service
public class GiantGenreServiceImpl implements GiantGenreService {
  
  @Autowired
  GiantBombGenreDao GenreDao;
  
  @Autowired
  private EntityManager em;

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public Set<GiantBombGenre> saveGenres(final Set<GiantBombGenre> Genres) {
    Set<GiantBombGenre> managed = null;
    if (null != Genres) {
      managed = new HashSet<>();
      for (GiantBombGenre pl : Genres) {
        managed.add(saveGenre(pl));
      }
    }
    return managed;
  }
  
  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public GiantBombGenre saveGenre(final GiantBombGenre Genre) {
    GiantBombGenre managed = null;
    if (null != Genre) {
      GiantBombGenre exist = GenreDao.findByGamebombId(Genre.getGamebombId());
      if (null != exist) {
        managed = updateGenre(exist, Genre);
      } else {
        managed = persistGenre(Genre);
      }
    }
    return managed;
  }
  
  private GiantBombGenre persistGenre(final GiantBombGenre Genre) {
    em.persist(Genre);
    return Genre;
  }

  private GiantBombGenre updateGenre(final GiantBombGenre exist, final GiantBombGenre Genre) {
    exist.fulfill(Genre);
    return em.merge(exist);
  }

  @Override
  public Set<GiantBombGenre> findGenresByStatus(GiantBombStatus status) {
    return GenreDao.findByStatus(status);
  }

  @Override
  public void updateStatus(GiantBombGenre Genre, GiantBombStatus status) {
    Genre.setStatus(status);
    GenreDao.save(Genre);
  }
}
