package com.elminster.grs.web.service.impl;

import java.sql.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elminster.grs.shared.db.dao.NotificationDao;
import com.elminster.grs.shared.db.domain.Notification;
import com.elminster.grs.shared.db.domain.Notification.NotificationStatus;
import com.elminster.grs.shared.db.domain.Notification.NotificationType;
import com.elminster.grs.web.service.NotificationService;
import com.elminster.grs.web.service.ServiceErrorCode;
import com.elminster.grs.web.service.ServiceException;

/**
 * The notification service implementation.
 * 
 * @author jgu
 * @version 1.0
 */
@Service
public class NotificationServiceImpl implements NotificationService {

  private static final Log logger = LogFactory.getLog(NotificationServiceImpl.class);

  @Autowired
  private NotificationDao notificationDao;

  @Override
  public List<Notification> getAllNotifications(int userId) throws ServiceException {
    try {
      return notificationDao.findByOwnerAndStatusIsNot(userId, NotificationStatus.DELETED);
    } catch (Exception e) {
      throw new ServiceException(ServiceErrorCode.DATABASE_EXCEPTION, e);
    }
  }

  @Override
  public List<Notification> getAllUnreadNotifications(int userId) throws ServiceException {
    try {
      return notificationDao.findByOwnerAndStatus(userId, NotificationStatus.UNREAD);
    } catch (Exception e) {
      throw new ServiceException(ServiceErrorCode.DATABASE_EXCEPTION, e);
    }
  }

  @Override
  public List<Notification> getAllMarkedNotifications(int userId) throws ServiceException {
    try {
      return notificationDao.findByOwnerAndStatus(userId, NotificationStatus.MARKED);
    } catch (Exception e) {
      throw new ServiceException(ServiceErrorCode.DATABASE_EXCEPTION, e);
    }
  }

  @Override
  public List<Notification> getAllBroadcastNotifications() throws ServiceException {
    try {
      return notificationDao.findByOwnerAndStatus(0, NotificationStatus.BROADCAST);
    } catch (Exception e) {
      throw new ServiceException(ServiceErrorCode.DATABASE_EXCEPTION, e);
    }
  }

  @Override
  public int sendNotification(int userId, String title, String content) throws ServiceException {
    return sendNotification(userId, title, content, NotificationType.INFO);
  }

  @Override
  @Transactional
  public int sendNotification(int userId, String title, String content, NotificationType type) throws ServiceException {
    Notification notification = new Notification(userId, title, content, type);
    try {
      // TODO send to MQ that will send the notification to web socket.
      return notificationDao.save(notification).getId();
    } catch (Exception e) {
      throw new ServiceException(ServiceErrorCode.DATABASE_EXCEPTION, e);
    }
  }

  @Override
  public int sendNotification(int userId, String content, NotificationType type) throws ServiceException {
    return sendNotification(userId, type.getValue(), content, type);
  }

  @Override
  @Transactional
  public int broadcastNotification(String title, String content, NotificationType type, Date expiration)
      throws ServiceException {
    Notification notification = new Notification(0, title, content, type);
    notification.setStatus(NotificationStatus.BROADCAST);
    try {
      // TODO send to MQ that will send the notification to web socket.
      return notificationDao.save(notification).getId();
    } catch (Exception e) {
      throw new ServiceException(ServiceErrorCode.DATABASE_EXCEPTION, e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void markNotification(int notificationId) throws ServiceException {
    changeNotifiacitonStatus(notificationId, NotificationStatus.MARKED);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void unmarkNotification(int notificationId) throws ServiceException {
    changeNotifiacitonStatus(notificationId, NotificationStatus.READ);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void readNotification(int notificationId) throws ServiceException {
    changeNotifiacitonStatus(notificationId, NotificationStatus.READ);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void unreadNotification(int notificationId) throws ServiceException {
    changeNotifiacitonStatus(notificationId, NotificationStatus.UNREAD);
  }

  /**
   * Change the notification status for specified notification, it will fail if the notification is already deleted or
   * it is a broadcast notification.
   * 
   * @param notificationId the notification id
   * @param status the notification status
   * @throws ServiceException on error.
   */
  @Transactional
  private void changeNotifiacitonStatus(int notificationId, NotificationStatus status) throws ServiceException {
    try {
      Notification notification = notificationDao.findOne(notificationId);
      if (null != notification) {
        NotificationStatus oldStatus = notification.getStatus();
        if (NotificationStatus.BROADCAST == oldStatus || NotificationStatus.DELETED == oldStatus) {
          String message = String.format("Cannot change status for notification [%d] since the illegal status.",
              notificationId);
          if (logger.isDebugEnabled()) {
            logger.debug(message);
          }
          throw new ServiceException(ServiceErrorCode.NOTIFCATION_ILLEGAL_STATUS, message);
        }
        if (oldStatus != status) {
          notification.setStatus(status);
          notificationDao.save(notification);
        }
      }
    } catch (Exception e) {
      if (e instanceof ServiceException) {
        throw e;
      } else {
        throw new ServiceException(ServiceErrorCode.DATABASE_EXCEPTION, e);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Transactional
  public void deleteNotification(int notificationId) throws ServiceException {
    try {
      Notification notification = notificationDao.findOne(notificationId);
      if (null != notification) {
        NotificationStatus status = notification.getStatus();
        if (NotificationStatus.MARKED == status) {
          String message = "Cannot delete marked notification. Please unmark it first.";
          if (logger.isDebugEnabled()) {
            logger.debug(message);
          }
          throw new ServiceException(ServiceErrorCode.DELETE_MARKED_NOTIFICATION, message);
        }
        if (NotificationStatus.DELETED != status) {
          notification.setStatus(NotificationStatus.DELETED);
          notificationDao.save(notification);
        }
      }
    } catch (Exception e) {
      if (e instanceof ServiceException) {
        throw e;
      } else {
        throw new ServiceException(ServiceErrorCode.DATABASE_EXCEPTION, e);
      }
    }
  }
}
