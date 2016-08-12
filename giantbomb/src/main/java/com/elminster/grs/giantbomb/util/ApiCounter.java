package com.elminster.grs.giantbomb.util;

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elminster.common.pool.ThreadPool;
import com.elminster.common.thread.IJobMonitor;
import com.elminster.common.thread.Job;

/**
 * An counter to count the api calls to prevent excess the api call limit.
 * 
 * @author jinggu
 * @version 1.0
 */
final public class ApiCounter {

  private static final Log logger = LogFactory.getLog(ApiCounter.class);
  private String username;
  private String password;
  private String apiKey;
  private String name;
  private long startTime;
  private Semaphore semaphore;
  private AtomicInteger counter;
  private int threshold;
  private long resetPeriod;
  private int resetCount;
  
  ApiCounter(String name, int threshold, long resetPeriod) {
    this.name = name;
    this.threshold = threshold;
    this.resetPeriod = resetPeriod;
    init();
  }
  
  ApiCounter(String name, String username, String password, int threshold, long resetPeriod) {
    this(name, threshold, resetPeriod);
    this.username = username;
    this.password = password;
  }
  
  ApiCounter(String name, String apiKey, int threshold, long resetPeriod) {
    this(name, threshold, resetPeriod);
    this.apiKey = apiKey;
  }
  
  
  private void init() {
    counter = new AtomicInteger(0);
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
    ThreadPool.getThreadPool().scheduleAtFixedRate(resetSemphoreJob, resetPeriod, resetPeriod, TimeUnit.MILLISECONDS);
  }
  
  public void acquire() {
    try {
      semaphore.acquire();
      counter.incrementAndGet();
    } catch (InterruptedException e) {
      logger.warn(e);
    }
  }
  
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getApiKey() {
    return apiKey;
  }

  public String getName() {
    return name;
  }

  public long getStartTime() {
    return startTime;
  }

  public AtomicInteger getCounter() {
    return counter;
  }

  public int getThreshold() {
    return threshold;
  }

  public long getResetPeriod() {
    return resetPeriod;
  }

  public int getResetCount() {
    return resetCount;
  }

  public String printStatus() {
    StringBuilder sb = new StringBuilder();
    long nextResetTime = startTime + ((resetCount + 1) * resetPeriod);
    sb.append(String.format("Api counter [%s] started at [%tc], reset [%d] time(s), next reset time [%tc]. \n",
        name, new Date(startTime), resetCount, new Date(nextResetTime)));
    sb.append(String.format("Semaphore status: available permits [%d], queue length [%d].",
        semaphore.availablePermits(), semaphore.getQueueLength()));
    return sb.toString();
  }
}
