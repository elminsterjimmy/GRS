package com.elminster.grs.giantbomb.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.elminster.grs.giantbomb.ds.GiantBombGame;

@Repository
public interface GiantBombGameDao extends BaseObjectRepository<GiantBombGame, Integer>, JpaSpecificationExecutor<GiantBombGame> {

}
