package com.elminster.grs.crawler.schedule;

import java.util.List;
import java.util.concurrent.Semaphore;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.elminster.common.pool.ThreadPool;
import com.elminster.common.thread.IJob;
import com.elminster.common.thread.IJobMonitor;
import com.elminster.common.thread.Job;
import com.elminster.common.util.DateUtil;
import com.elminster.grs.crawler.service.GameInformationUpdateService;
import com.elminster.grs.shared.db.dao.UserGameMetaDao;
import com.elminster.grs.shared.db.domain.UserGameMeta;

/**
 * The auto update for user's game information.
 * 
 * @author jgu
 * @version 1.0
 */
@Component
public class UserGameInfoUpdaterScheduler {
  
  private static final long SCHEDULE_FIXED_RATE = 2 * DateUtil.HOUR;
  private static final int MAX_THREAD_COUNT = 20;
  private static final Semaphore semaphore = new Semaphore(MAX_THREAD_COUNT);
  private static final Log logger = LogFactory.getLog(UserGameInfoUpdaterScheduler.class);
  
  @Autowired
  private GameInformationUpdateService gameInfoUpdateService;
  
  @Autowired
  private UserGameMetaDao userGameMetaDao;
  
  @Scheduled(fixedRate = SCHEDULE_FIXED_RATE)
  public void updateUserInfo() {
    long userCount = userGameMetaDao.count();
    for (int i = 0; i <= userCount / MAX_THREAD_COUNT; i++) {
      Pageable pageable = new PageRequest(i, MAX_THREAD_COUNT);
      final Page<UserGameMeta> page = userGameMetaDao.findAll(pageable);
      try {
        semaphore.acquire();
      } catch (InterruptedException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      // each thread will deal with 20 accounts.
      IJob job = new Job(i, "Update User Game Metas") {

        @Override
        protected JobStatus doWork(IJobMonitor monitor) {
          try {
            int totalCount = page.getNumberOfElements();
            monitor.beginJob(String.format("Update User Game Metas page [%d]", page.getNumber()), totalCount);
            List<UserGameMeta> content = page.getContent();
            for (int j = 0; j < totalCount; j++) {
              UserGameMeta userGamemeta = content.get(j);
              try {
                gameInfoUpdateService.updateGameInformation(userGamemeta.getUserId());
              } catch (Exception e) {
                // TODO logging and ignore
                e.printStackTrace();
              }
            }
            return monitor.done();
          } finally {
            semaphore.release();
          }
        }
      };
      ThreadPool.getThreadPool().execute(job);
    }
  }
}
