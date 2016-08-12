package com.elminster.grs.giantbomb.service;

import java.util.Set;

import com.elminster.grs.giantbomb.ds.GiantBombTheme;
import com.elminster.grs.giantbomb.ds.GiantBombStatus;

public interface GiantThemeService {

  public Set<GiantBombTheme> saveThemes(Set<GiantBombTheme> Themes);

  public GiantBombTheme saveTheme(GiantBombTheme Theme);

  public Set<GiantBombTheme> findThemesByStatus(GiantBombStatus status);

  public void updateStatus(GiantBombTheme Theme, GiantBombStatus status);

}
