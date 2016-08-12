package com.elminster.grs.giantbomb.service.job;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elminster.common.thread.IJobMonitor;
import com.elminster.common.thread.Job;
import com.elminster.common.util.DateUtil;
import com.elminster.common.util.ExceptionUtil;
import com.elminster.grs.giantbomb.config.CrawlJobId;
import com.elminster.grs.giantbomb.ds.GiantBombCompany;
import com.elminster.grs.giantbomb.ds.GiantBombStatus;
import com.elminster.grs.giantbomb.service.CollectConf;
import com.elminster.grs.giantbomb.service.GiantBombCollectService;
import com.elminster.grs.giantbomb.service.GiantCompanyService;

public class CollectDetailCompanyInfoJob extends Job {
  
  private static final Log logger = LogFactory.getLog(CollectDetailCompanyInfoJob.class);
  
  private CollectConf conf;
  private GiantBombCollectService collectionService;
  private GiantCompanyService CompanyService;
  
  public CollectDetailCompanyInfoJob(
      long id,
      String name,
      CollectConf conf,
      GiantBombCollectService collectionService,
      GiantCompanyService CompanyService) {
    super(CrawlJobId.COLLECT_DETAIL_COMPANY_INFO_JOB_ID, "Collect detail Company information");
    this.conf = conf;
    this.collectionService = collectionService;
    this.CompanyService = CompanyService;
  }

  @Override
  protected JobStatus doWork(IJobMonitor monitor) {
    while (!monitor.isCancelled() && !Thread.interrupted()) {
      Set<GiantBombCompany> Companys = CompanyService.findCompanysByStatus(GiantBombStatus.BASIC_INFO_CRAWLED);
      monitor.beginJob("collect detail Company information", Companys.size());
      if (!Companys.isEmpty()) {
        for (GiantBombCompany Company : Companys) {
          try {
            collectionService.collectDetailCompanyInfo(conf, Company);
            CompanyService.updateStatus(Company, GiantBombStatus.DETAIL_INFO_CRAWLED);
            monitor.worked(1);
          } catch (Exception e) {
            logger.error(String.format("Failed to crawl Company [%s]. Cause [%s].", Company.getName(), ExceptionUtil.getFullStackTrace(e)));
          }
        }
      } else {
        try {
          logger.debug("No Crawled Basic Company Information available, wait 10 mins to retry.");
          Thread.sleep(10 * DateUtil.MINUTE);
        } catch (InterruptedException e) {
          logger.error("Detail Company Collect Job is Interrupted.");
          return monitor.cancel();
        }
      }
    }
    return monitor.done();
  }
}