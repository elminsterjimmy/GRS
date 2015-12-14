package com.elminster.grs.giantbomb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.elminster.grs.giantbomb.ds.SingleVideo;

@Repository
public interface GiantBombVideoDao extends JpaRepository<SingleVideo, Integer>, JpaSpecificationExecutor<SingleVideo> {

  public SingleVideo findByGamebombId(int gbId);
}
