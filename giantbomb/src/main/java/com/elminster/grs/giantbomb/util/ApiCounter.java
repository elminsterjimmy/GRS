package com.elminster.grs.giantbomb.util;

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
  private Long firstApiCallTime;
  private Long lastApiCallTime;
  private Semaphore semaphore;
  private AtomicInteger counter;
  private int threshold;
  private long resetPeriod;
  private int resetCount;
  private volatile boolean resetJobStarted = false;
  private Lock lock = new ReentrantLock();
  private Job resetSemphoreJob;

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
    this.resetSemphoreJob = new Job(0x0F010, String.format("API counter for [%s]", name)) {

      @Override
      protected JobStatus doWork(IJobMonitor monitor) {
        monitor.beginJob(String.format("reset API counter: [%s].", name), 1);
        semaphore.release(counter.get());
        resetCount++;
        monitor.worked(1);
        return monitor.done();
      }
    };
  }

  public void acquire() {
    try {
      if (!resetJobStarted) {
        try {
          lock.lock();
          if (!resetJobStarted) {
            firstApiCallTime = System.currentTimeMillis();
            ThreadPool.getThreadPool().scheduleAtFixedRate(resetSemphoreJob, resetPeriod, resetPeriod, TimeUnit.MILLISECONDS);
            resetJobStarted = true;
          }
        } finally {
          lock.unlock();
        }
      }
      semaphore.acquire();
      lastApiCallTime = System.currentTimeMillis();
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
    
    
    String firstApiCalledDate = null == firstApiCallTime ?
        "API still not be called." : String.format("%tc", new Date(firstApiCallTime));
    String lastApiCalledDate = null == lastApiCallTime ?
        "API still not be called." : String.format("%tc", new Date(lastApiCallTime));
    String nextApiResetDate = null == firstApiCallTime ? 
        "API still not be called." : String.format("%tc", new Date(firstApiCallTime + ((resetCount + 1) * resetPeriod)));
    sb.append(String.format("Api counter [%s] started at [%tc], reset [%d] time(s) and next reset will be at [%s]. \n", name, new Date(startTime), resetCount, nextApiResetDate));
    sb.append(String.format("First API is called at [%s], last API is called at [%s]. \n", firstApiCalledDate, lastApiCalledDate));
    sb.append(String.format("Semaphore status: available permits [%d], queue length [%d].", semaphore.availablePermits(), semaphore.getQueueLength()));
    return sb.toString();
  }
  
  public String toString() {
    return printStatus();
  }
}
