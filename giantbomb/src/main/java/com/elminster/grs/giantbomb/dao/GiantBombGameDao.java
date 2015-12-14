package com.elminster.grs.giantbomb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.elminster.grs.giantbomb.ds.SingleGame;

@Repository
public interface GiantBombGameDao extends JpaRepository<SingleGame, Integer>, JpaSpecificationExecutor<SingleGame> {

  public SingleGame findByGamebombId(int gbId);
}
