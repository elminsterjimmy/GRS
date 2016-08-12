package com.elminster.grs.giantbomb.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.elminster.grs.giantbomb.ds.GiantBombTheme;

@Repository
public interface GiantBombThemeDao extends BaseObjectRepository<GiantBombTheme, Integer>, JpaSpecificationExecutor<GiantBombTheme> {

}
