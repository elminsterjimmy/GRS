package com.elminster.grs.giantbomb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.elminster.grs.giantbomb.ds.GiantBombTheme;

@Repository
public interface GiantBombThemeDao extends JpaRepository<GiantBombTheme, Integer>, JpaSpecificationExecutor<GiantBombTheme> {

  public GiantBombTheme findByGamebombId(int gbId);
}
