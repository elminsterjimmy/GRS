package com.elminster.grs.giantbomb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.elminster.grs.giantbomb.ds.GiantBombPlatform;

@Repository
public interface GiantBombPlatformDao extends JpaRepository<GiantBombPlatform, Integer>, JpaSpecificationExecutor<GiantBombPlatform> {

  public GiantBombPlatform findByGamebombId(int gbId);

}
