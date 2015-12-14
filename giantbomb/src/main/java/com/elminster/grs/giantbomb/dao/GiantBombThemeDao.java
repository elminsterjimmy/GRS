package com.elminster.grs.giantbomb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.elminster.grs.giantbomb.ds.SingleTheme;

@Repository
public interface GiantBombThemeDao extends JpaRepository<SingleTheme, Integer>, JpaSpecificationExecutor<SingleTheme> {

  public SingleTheme findByGamebombId(int gbId);
}
