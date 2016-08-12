package com.elminster.grs.giantbomb.service;

import java.util.Set;

import com.elminster.grs.giantbomb.ds.GiantBombStatus;
import com.elminster.grs.giantbomb.ds.GiantBombVideo;

public interface GiantVideoService {

  public Set<GiantBombVideo> saveVideos(Set<GiantBombVideo> videos);

  public GiantBombVideo saveVideo(GiantBombVideo video);

  public GiantBombVideo findVideoByGamebombId(int gbId);

  public Set<GiantBombVideo> findVideosByStatus(GiantBombStatus basicInfoCrawled);

  public void updateStatus(GiantBombVideo video, GiantBombStatus detailInfoCrawled);
}
