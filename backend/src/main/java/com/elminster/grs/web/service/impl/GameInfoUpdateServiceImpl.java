package com.elminster.grs.web.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.elminster.common.exception.BaseException;
import com.elminster.common.exception.ErrorCode;
import com.elminster.common.util.DateUtil;
import com.elminster.grs.web.dao.GameInfoUpdateHistoryDao;
import com.elminster.grs.web.domain.GameInfoUpdateHistory;
import com.elminster.grs.web.domain.helper.UpdateHistoryStatusHelper;
import com.elminster.grs.web.handler.UserInformationUpdateException;
import com.elminster.grs.web.handler.UserInformationUpdater;
import com.elminster.grs.web.service.GameInfoUpdateService;
import com.elminster.grs.web.service.ServiceErrorCode;
import com.elminster.grs.web.service.ServiceException;

/**
 * The service to update the users game informations.
 * 
 * @author jgu
 * @version 1.0
 */
@Service
@Transactional
public class GameInfoUpdateServiceImpl implements GameInfoUpdateService {

  /** deafult update threshold 2 hours. */
  private static final long DEAFULT_UPDATE_THRESHOLD = 2 * DateUtil.HOUR;
  /** the logger. */
  private static final Log logger = LogFactory.getLog(GameInfoUpdateServiceImpl.class);
  @Autowired
  private GameInfoUpdateHistoryDao updateHistoryDao;
  @Autowired
  @Qualifier("PsnGameInformationUpdater")
  private UserInformationUpdater psnUpdater;
  /** the update threshold. */
  private long threshold = DEAFULT_UPDATE_THRESHOLD;

  /**
   * {@inheritDoc}
   */
  @Override
  public void updateUserGame(int userId) throws ServiceException {
    // first get the update history
    GameInfoUpdateHistory updateHistory = updateHistoryDao.findByUserId(userId);
    int status;
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
      status = 0;
    }

    try {
      if (logger.isDebugEnabled()) {
        logger.debug(String.format("Start Updating game info for user %d.", userId));
      }
      psnUpdater.updateUserInform(userId);
      updateHistory.setStatus(UpdateHistoryStatusHelper.updatePsnProfileSuccess(status));
    } catch (UserInformationUpdateException e) {
      updateHistory.setStatus(UpdateHistoryStatusHelper.updatePsnProfileFailed(status));
      ErrorCode ec = e.getErrorCode();
      updateHistory.setErrorCode(null == ec ? BaseException.UNKNOWN_CODE.getCode() : ec.getCode());
      throw new ServiceException(ServiceErrorCode.UPDATE_USER_GAME_INFO_EXCEPTION, e);
    } finally {
      if (logger.isDebugEnabled()) {
        logger.debug(String.format("End Updating game info for user %d.", userId));
      }
    }

    updateHistory.setLastUpdate(new Date(System.currentTimeMillis()));
    updateHistoryDao.save(updateHistory);
  }

}
