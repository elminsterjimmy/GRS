package com.elminster.grs.giantbomb.util;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elminster.common.pool.ThreadPool;
import com.elminster.common.thread.IJobMonitor;
import com.elminster.common.thread.Job;

final public class ApiCounter {

  private static final Log logger = LogFactory.getLog(ApiCounter.class);
  private String name;
  private long startTime;
  private Semaphore semaphore;
  private AtomicInteger counter;
  private int resetCount;
  
  public ApiCounter(String name, int threshold) {
    this.name = name;
    this.semaphore = new Semaphore(threshold);
    this.startTime = System.currentTimeMillis();
    Job resetSemphoreJob = new Job(0x00010, String.format("API counter for [%s]", name)) {

      @Override
      protected JobStatus doWork(IJobMonitor monitor) {
        monitor.beginJob("reset API counter", 1);
        semaphore.release(counter.get());
        resetCount++;
        monitor.worked(1);
        return monitor.done();
      }
      
    };
    ThreadPool.getThreadPool().scheduleAtFixedRate(resetSemphoreJob, 0, 1, TimeUnit.HOURS);
  }
  
  public void acquire() {
    try {
      semaphore.acquire();
      counter.incrementAndGet();
    } catch (InterruptedException e) {
      logger.warn(e);
    }
  }
  
  public String printStatus() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("Api counter [%s] started at [%d], reset [%d] time(s). \n", name, startTime, resetCount));
    sb.append(String.format("Semaphore status: available permits [%d], queue length [%s].", semaphore.availablePermits(), semaphore.getQueueLength()));
    return sb.toString();
  }
}
