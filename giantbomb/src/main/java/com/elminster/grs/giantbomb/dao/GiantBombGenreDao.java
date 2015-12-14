package com.elminster.grs.giantbomb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.elminster.grs.giantbomb.ds.SingleGenre;

@Repository
public interface GiantBombGenreDao extends JpaRepository<SingleGenre, Integer>, JpaSpecificationExecutor<SingleGenre> {

  public SingleGenre findByGamebombId(int gbId);
}
