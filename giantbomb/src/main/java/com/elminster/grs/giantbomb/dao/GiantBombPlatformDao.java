package com.elminster.grs.giantbomb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.elminster.grs.giantbomb.ds.SinglePlatform;

@Repository
public interface GiantBombPlatformDao extends JpaRepository<SinglePlatform, Integer>, JpaSpecificationExecutor<SinglePlatform> {

  public SinglePlatform findByGamebombId(int gbId);

}
