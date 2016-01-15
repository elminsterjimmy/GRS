package com.elminster.grs.crawler.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elminster.common.exception.BaseException;
import com.elminster.common.exception.ErrorCode;
import com.elminster.common.util.DateUtil;
import com.elminster.grs.crawler.service.GameInformationUpdateException;
import com.elminster.grs.crawler.service.GameInformationUpdateService;
import com.elminster.grs.crawler.service.ServiceErrorCode;
import com.elminster.grs.crawler.updater.GameInformationUpdater;
import com.elminster.grs.crawler.updater.InformationUpdateException;
import com.elminster.grs.shared.db.dao.GameInfoUpdateHistoryDao;
import com.elminster.grs.shared.db.domain.GameInfoUpdateHistory;
import com.elminster.grs.shared.db.domain.GameInfoUpdateHistory.UpdateStatus;
import com.elminster.grs.shared.db.domain.helper.UpdateHistoryStatusHelper;

@Service
public class GameInformationUpdateServiceImpl implements GameInformationUpdateService {
  
  /** default update threshold 2 hours. */
  private static final long DEAFULT_UPDATE_THRESHOLD = 2 * DateUtil.HOUR;
  /** the logger. */
  private static final Log logger = LogFactory.getLog(GameInformationUpdateServiceImpl.class);
  @Autowired
  private GameInfoUpdateHistoryDao updateHistoryDao;
  @Autowired
  private GameInformationUpdater<Integer> psnUpdater;
  /** the update threshold. */
  private volatile long threshold = DEAFULT_UPDATE_THRESHOLD;

  /**
   * {@inheritDoc}
   */
  @Override
  @Transactional
  public void updateGameInformation(Integer userId) throws GameInformationUpdateException {
    // first get the update history
    GameInfoUpdateHistory updateHistory = updateHistoryDao.findByUserId(userId);
    UpdateStatus status;
    if (null != updateHistory) {
      Date lastUpdate = updateHistory.getLastUpdate();
      status = updateHistory.getStatus();
      if (System.currentTimeMillis() - lastUpdate.getTime() < threshold
          && UpdateHistoryStatusHelper.isPsnProfileUpdated(status)) {
        if (logger.isDebugEnabled()) {
          logger.debug(String.format("Already updated for user %d at %s. Will not update this time.", userId,
              DateUtil.getDateString(lastUpdate)));
        }
        return;
      }
    } else {
      updateHistory = new GameInfoUpdateHistory();
      updateHistory.setUserId(userId);
    }

    try {
      if (logger.isDebugEnabled()) {
        logger.debug(String.format("Start Updating game info for user %d.", userId));
      }
      psnUpdater.updateInformation(userId);
      updateHistory.setStatus(UpdateStatus.UPDATE_PSN_PROFILE_SUCCESS);
    } catch (InformationUpdateException e) {
      updateHistory.setStatus(UpdateStatus.UPDATE_PSN_PROFILE_FAILED);
      ErrorCode ec = e.getErrorCode();
      updateHistory.setErrorCode(null == ec ? BaseException.UNKNOWN_CODE.getCode() : ec.getCode());
      throw new GameInformationUpdateException(ServiceErrorCode.UPDATE_USER_GAME_INFO_EXCEPTION, e);
    } finally {
      if (logger.isDebugEnabled()) {
        logger.debug(String.format("End Updating game info for user %d.", userId));
      }
    }

    updateHistory.setLastUpdate(new Date(System.currentTimeMillis()));
    updateHistoryDao.save(updateHistory);
  }

  @Override
  public void setUpdateThreshold(long thresholdInMs) {
    this.threshold = thresholdInMs;
  }

}
