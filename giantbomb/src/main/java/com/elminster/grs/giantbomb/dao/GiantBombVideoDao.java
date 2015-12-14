package com.elminster.grs.giantbomb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.elminster.grs.giantbomb.ds.GiantBombVideo;

@Repository
public interface GiantBombVideoDao extends JpaRepository<GiantBombVideo, Integer>, JpaSpecificationExecutor<GiantBombVideo> {

  public GiantBombVideo findByGamebombId(int gbId);
}
