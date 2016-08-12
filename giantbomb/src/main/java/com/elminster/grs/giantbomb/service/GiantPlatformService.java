package com.elminster.grs.giantbomb.service;

import java.util.Set;

import com.elminster.grs.giantbomb.ds.GiantBombPlatform;
import com.elminster.grs.giantbomb.ds.GiantBombStatus;

public interface GiantPlatformService {

  public Set<GiantBombPlatform> savePlatforms(Set<GiantBombPlatform> platforms);

  public GiantBombPlatform savePlatform(GiantBombPlatform platform);

  public Set<GiantBombPlatform> findPlatformsByStatus(GiantBombStatus status);

  public void updateStatus(GiantBombPlatform platform, GiantBombStatus status);
}
