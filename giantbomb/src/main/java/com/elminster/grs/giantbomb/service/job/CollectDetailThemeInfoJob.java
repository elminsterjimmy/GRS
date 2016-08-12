package com.elminster.grs.giantbomb.service.job;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elminster.common.thread.IJobMonitor;
import com.elminster.common.thread.Job;
import com.elminster.common.util.DateUtil;
import com.elminster.common.util.ExceptionUtil;
import com.elminster.grs.giantbomb.config.CrawlJobId;
import com.elminster.grs.giantbomb.ds.GiantBombTheme;
import com.elminster.grs.giantbomb.ds.GiantBombStatus;
import com.elminster.grs.giantbomb.service.CollectConf;
import com.elminster.grs.giantbomb.service.GiantBombCollectService;
import com.elminster.grs.giantbomb.service.GiantThemeService;

public class CollectDetailThemeInfoJob extends Job {
  
  private static final Log logger = LogFactory.getLog(CollectDetailThemeInfoJob.class);
  
  private CollectConf conf;
  private GiantBombCollectService collectionService;
  private GiantThemeService ThemeService;
  
  public CollectDetailThemeInfoJob(
      long id,
      String name,
      CollectConf conf,
      GiantBombCollectService collectionService,
      GiantThemeService ThemeService) {
    super(CrawlJobId.COLLECT_DETAIL_THEME_INFO_JOB_ID, "Collect detail Theme information");
    this.conf = conf;
    this.collectionService = collectionService;
    this.ThemeService = ThemeService;
  }

  @Override
  protected JobStatus doWork(IJobMonitor monitor) {
    while (!monitor.isCancelled() && !Thread.interrupted()) {
      Set<GiantBombTheme> Themes = ThemeService.findThemesByStatus(GiantBombStatus.BASIC_INFO_CRAWLED);
      monitor.beginJob("collect detail Theme information", Themes.size());
      if (!Themes.isEmpty()) {
        for (GiantBombTheme Theme : Themes) {
          try {
            collectionService.collectDetailThemeInfo(conf, Theme);
            ThemeService.updateStatus(Theme, GiantBombStatus.DETAIL_INFO_CRAWLED);
            monitor.worked(1);
          } catch (Exception e) {
            logger.error(String.format("Failed to crawl Theme [%s]. Cause [%s].", Theme.getName(), ExceptionUtil.getFullStackTrace(e)));
          }
        }
      } else {
        try {
          logger.debug("No Crawled Basic Theme Information available, wait 10 mins to retry.");
          Thread.sleep(10 * DateUtil.MINUTE);
        } catch (InterruptedException e) {
          logger.error("Detail Theme Collect Job is Interrupted.");
          return monitor.cancel();
        }
      }
    }
    return monitor.done();
  }
}