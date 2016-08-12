package com.elminster.grs.giantbomb.service;

import java.util.Set;

import com.elminster.grs.giantbomb.ds.GiantBombGenre;
import com.elminster.grs.giantbomb.ds.GiantBombStatus;

public interface GiantGenreService {

  public Set<GiantBombGenre> saveGenres(Set<GiantBombGenre> Genres);

  public GiantBombGenre saveGenre(GiantBombGenre Genre);

  public Set<GiantBombGenre> findGenresByStatus(GiantBombStatus status);

  public void updateStatus(GiantBombGenre Genre, GiantBombStatus status);

}
