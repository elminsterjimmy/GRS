package com.elminster.grs.giantbomb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elminster.grs.giantbomb.ds.GiantBombImage;

@Repository
public interface GiantBombImageDao extends JpaRepository<GiantBombImage, Integer>, JpaSpecificationExecutor<GiantBombImage> {

  @Query(value = "select count(1) > 0 from gaintbomb_game_extra_image where game_id = ?1", nativeQuery = true)
  public boolean existImageForGame(int gameId);
}
