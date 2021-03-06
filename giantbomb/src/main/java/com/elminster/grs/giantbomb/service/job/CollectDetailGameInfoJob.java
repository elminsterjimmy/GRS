package com.elminster.grs.giantbomb.service.job;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elminster.common.thread.IJobMonitor;
import com.elminster.common.thread.Job;
import com.elminster.common.util.DateUtil;
import com.elminster.common.util.ExceptionUtil;
import com.elminster.grs.giantbomb.config.CrawlJobId;
import com.elminster.grs.giantbomb.ds.GiantBombGame;
import com.elminster.grs.giantbomb.ds.GiantBombStatus;
import com.elminster.grs.giantbomb.service.GiantGameService;
import com.elminster.grs.giantbomb.service.CollectConf;
import com.elminster.grs.giantbomb.service.GiantBombCollectService;

public class CollectDetailGameInfoJob extends Job {
  
  private static final Log logger = LogFactory.getLog(CollectDetailGameInfoJob.class);
  
  private CollectConf conf;
  private GiantBombCollectService collectionService;
  private GiantGameService gameService;
  
  public CollectDetailGameInfoJob(
      long id,
      String name,
      CollectConf conf,
      GiantBombCollectService collectionService,
      GiantGameService gameService) {
    super(CrawlJobId.COLLECT_DETAIL_GAME_INFO_JOB_ID, "Collect detail game information");
    this.conf = conf;
    this.collectionService = collectionService;
    this.gameService = gameService;
  }

  @Override
  protected JobStatus doWork(IJobMonitor monitor) {
    while (!monitor.isCancelled() && !Thread.interrupted()) {
      Set<GiantBombGame> games = gameService.findGamesByStatus(GiantBombStatus.BASIC_INFO_CRAWLED);
      monitor.beginJob("collect detail game information", games.size());
      if (!games.isEmpty()) {
        for (GiantBombGame game : games) {
          try {
            collectionService.collectDetailGameInfo(conf, game);
            gameService.updateStatus(game, GiantBombStatus.DETAIL_INFO_CRAWLED);
            monitor.worked(1);
          } catch (Exception e) {
            logger.error(String.format("Failed to crawl game [%s]. Cause [%s].", game.getName(), ExceptionUtil.getFullStackTrace(e)));
          }
        }
      } else {
        try {
          logger.debug("No Crawled Basic Game Information available, wait 10 mins to retry.");
          Thread.sleep(10 * DateUtil.MINUTE);
        } catch (InterruptedException e) {
          logger.error("Detail Game Collect Job is Interrupted.");
          return monitor.cancel();
        }
      }
    }
    return monitor.done();
  }
}