package com.elminster.grs.crawler.job;

import com.elminster.common.thread.IJobMonitor;
import com.elminster.common.thread.Job;
import com.elminster.grs.crawler.config.SystemConfiguration;
import com.elminster.retrieve.web.DefaultWebRetriever;
import com.elminster.retrieve.web.WebRetriever;

public class GamespotCrawlJob extends Job {
  
  private static final SystemConfiguration configuration = SystemConfiguration.INSTANCE;
  
  public GamespotCrawlJob(int id) {
    super(id, "Gamespot crawler");
  }
  
  @Override
  protected JobStatus doWork(IJobMonitor monitor) {
    
    
    
    // TODO Auto-generated method stub
    return null;
  }
}
