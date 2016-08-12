package com.elminster.grs.giantbomb.service.job;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elminster.common.thread.IJobMonitor;
import com.elminster.common.thread.Job;
import com.elminster.common.util.DateUtil;
import com.elminster.common.util.ExceptionUtil;
import com.elminster.grs.giantbomb.config.CrawlJobId;
import com.elminster.grs.giantbomb.ds.GiantBombGenre;
import com.elminster.grs.giantbomb.ds.GiantBombStatus;
import com.elminster.grs.giantbomb.service.CollectConf;
import com.elminster.grs.giantbomb.service.GiantBombCollectService;
import com.elminster.grs.giantbomb.service.GiantGenreService;

public class CollectDetailGenreInfoJob extends Job {
  
  private static final Log logger = LogFactory.getLog(CollectDetailGenreInfoJob.class);
  
  private CollectConf conf;
  private GiantBombCollectService collectionService;
  private GiantGenreService GenreService;
  
  public CollectDetailGenreInfoJob(
      long id,
      String name,
      CollectConf conf,
      GiantBombCollectService collectionService,
      GiantGenreService GenreService) {
    super(CrawlJobId.COLLECT_DETAIL_GENRE_INFO_JOB_ID, "Collect detail Genre information");
    this.conf = conf;
    this.collectionService = collectionService;
    this.GenreService = GenreService;
  }

  @Override
  protected JobStatus doWork(IJobMonitor monitor) {
    while (!monitor.isCancelled() && !Thread.interrupted()) {
      Set<GiantBombGenre> Genres = GenreService.findGenresByStatus(GiantBombStatus.BASIC_INFO_CRAWLED);
      monitor.beginJob("collect detail Genre information", Genres.size());
      if (!Genres.isEmpty()) {
        for (GiantBombGenre Genre : Genres) {
          try {
            collectionService.collectDetailGenreInfo(conf, Genre);
            GenreService.updateStatus(Genre, GiantBombStatus.DETAIL_INFO_CRAWLED);
            monitor.worked(1);
          } catch (Exception e) {
            logger.error(String.format("Failed to crawl Genre [%s]. Cause [%s].", Genre.getName(), ExceptionUtil.getFullStackTrace(e)));
          }
        }
      } else {
        try {
          logger.debug("No Crawled Basic Genre Information available, wait 10 mins to retry.");
          Thread.sleep(10 * DateUtil.MINUTE);
        } catch (InterruptedException e) {
          logger.error("Detail Genre Collect Job is Interrupted.");
          return monitor.cancel();
        }
      }
    }
    return monitor.done();
  }
}