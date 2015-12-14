package com.elminster.grs.giantbomb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.elminster.grs.giantbomb.ds.GiantBombGenre;

@Repository
public interface GiantBombGenreDao extends JpaRepository<GiantBombGenre, Integer>, JpaSpecificationExecutor<GiantBombGenre> {

  public GiantBombGenre findByGamebombId(int gbId);
}