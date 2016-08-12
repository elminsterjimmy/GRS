package com.elminster.grs.giantbomb.service.job;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elminster.common.thread.IJobMonitor;
import com.elminster.common.thread.Job;
import com.elminster.common.util.DateUtil;
import com.elminster.common.util.ExceptionUtil;
import com.elminster.grs.giantbomb.config.CrawlJobId;
import com.elminster.grs.giantbomb.ds.GiantBombPlatform;
import com.elminster.grs.giantbomb.ds.GiantBombStatus;
import com.elminster.grs.giantbomb.service.CollectConf;
import com.elminster.grs.giantbomb.service.GiantBombCollectService;
import com.elminster.grs.giantbomb.service.GiantPlatformService;

public class CollectDetailPlatformInfoJob extends Job {
  
  private static final Log logger = LogFactory.getLog(CollectDetailPlatformInfoJob.class);
  
  private CollectConf conf;
  private GiantBombCollectService collectionService;
  private GiantPlatformService platformService;
  
  public CollectDetailPlatformInfoJob(
      long id,
      String name,
      CollectConf conf,
      GiantBombCollectService collectionService,
      GiantPlatformService platformService) {
    super(CrawlJobId.COLLECT_DETAIL_PLATFORM_INFO_JOB_ID, "Collect detail platform information");
    this.conf = conf;
    this.collectionService = collectionService;
    this.platformService = platformService;
  }

  @Override
  protected JobStatus doWork(IJobMonitor monitor) {
    while (!monitor.isCancelled() && !Thread.interrupted()) {
      Set<GiantBombPlatform> platforms = platformService.findPlatformsByStatus(GiantBombStatus.BASIC_INFO_CRAWLED);
      monitor.beginJob("collect detail platform information", platforms.size());
      if (!platforms.isEmpty()) {
        for (GiantBombPlatform platform : platforms) {
          try {
            collectionService.collectDetailPlatformInfo(conf, platform);
            platformService.updateStatus(platform, GiantBombStatus.DETAIL_INFO_CRAWLED);
            monitor.worked(1);
          } catch (Exception e) {
            logger.error(String.format("Failed to crawl platform [%s]. Cause [%s].", platform.getName(), ExceptionUtil.getFullStackTrace(e)));
          }
        }
      } else {
        try {
          logger.debug("No Crawled Basic Platform Information available, wait 10 mins to retry.");
          Thread.sleep(10 * DateUtil.MINUTE);
        } catch (InterruptedException e) {
          logger.error("Detail Platform Collect Job is Interrupted.");
          return monitor.cancel();
        }
      }
    }
    return monitor.done();
  }
}