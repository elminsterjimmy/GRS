package com.elminster.grs.giantbomb.service.job;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elminster.common.thread.IJobMonitor;
import com.elminster.common.thread.Job;
import com.elminster.common.util.DateUtil;
import com.elminster.common.util.ExceptionUtil;
import com.elminster.grs.giantbomb.config.CrawlJobId;
import com.elminster.grs.giantbomb.ds.GiantBombVideo;
import com.elminster.grs.giantbomb.ds.GiantBombStatus;
import com.elminster.grs.giantbomb.service.CollectConf;
import com.elminster.grs.giantbomb.service.GiantBombCollectService;
import com.elminster.grs.giantbomb.service.GiantVideoService;

public class CollectDetailVideoInfoJob extends Job {
  
  private static final Log logger = LogFactory.getLog(CollectDetailVideoInfoJob.class);
  
  private CollectConf conf;
  private GiantBombCollectService collectionService;
  private GiantVideoService VideoService;
  
  public CollectDetailVideoInfoJob(
      long id,
      String name,
      CollectConf conf,
      GiantBombCollectService collectionService,
      GiantVideoService VideoService) {
    super(CrawlJobId.COLLECT_DETAIL_VIDEO_INFO_JOB_ID, "Collect detail Video information");
    this.conf = conf;
    this.collectionService = collectionService;
    this.VideoService = VideoService;
  }

  @Override
  protected JobStatus doWork(IJobMonitor monitor) {
    while (!monitor.isCancelled() && !Thread.interrupted()) {
      Set<GiantBombVideo> Videos = VideoService.findVideosByStatus(GiantBombStatus.BASIC_INFO_CRAWLED);
      monitor.beginJob("collect detail Video information", Videos.size());
      if (!Videos.isEmpty()) {
        for (GiantBombVideo Video : Videos) {
          try {
            collectionService.collectDetailVideoInfo(conf, Video);
            VideoService.updateStatus(Video, GiantBombStatus.DETAIL_INFO_CRAWLED);
            monitor.worked(1);
          } catch (Exception e) {
            logger.error(String.format("Failed to crawl Video [%s]. Cause [%s].", Video.getName(), ExceptionUtil.getFullStackTrace(e)));
          }
        }
      } else {
        try {
          logger.debug("No Crawled Basic Video Information available, wait 10 mins to retry.");
          Thread.sleep(10 * DateUtil.MINUTE);
        } catch (InterruptedException e) {
          logger.error("Detail Video Collect Job is Interrupted.");
          return monitor.cancel();
        }
      }
    }
    return monitor.done();
  }
}