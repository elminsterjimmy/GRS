package com.elminster.grs.web.service;

import java.sql.Date;
import java.util.List;

import com.elminster.grs.web.domain.Notification;
import com.elminster.grs.web.domain.Notification.NotificationType;

/**
 * The notification service.
 * 
 * @author jgu
 * @version 1.0
 */
public interface NotificationService {

  /**
   * Get all notifications for the user.
   * @param userId the user id
   * @return all notifications for the user
   * @throws ServiceException on error
   */
  public List<Notification> getAllNotifications(int userId) throws ServiceException;
  
  /**
   * Get all unread notifications for the user.
   * @param userId the user id
   * @return all unread notifications for the user
   * @throws ServiceException on error
   */
  public List<Notification> getAllUnreadNotifications(int userId) throws ServiceException;
  
  /**
   * Get all marked notifications for the user.
   * @param userId the user id
   * @return all marked notifications for the user
   * @throws ServiceException on error
   */
  public List<Notification> getAllMarkedNotifications(int userId) throws ServiceException;
  
  /**
   * Get all broadcast notifications.
   * @return all broadcast notifications
   * @throws ServiceException on error
   */
  public List<Notification> getAllBroadcastNotifications() throws ServiceException;
  
  /**
   * Send notification with specified title and content to user.
   * @param userId the user id
   * @param title the notification title
   * @param content the notification content
   * @return the notification id
   * @throws ServiceException on error
   */
  public int sendNotification(int userId, String title, String content) throws ServiceException;
  
  /**
   * Send notification with specified title, content and type to user.
   * @param userId the user id
   * @param title the notification title
   * @param content the notification content
   * @param type the notification type
   * @return the notification id
   * @throws ServiceException on error
   */
  public int sendNotification(int userId, String title, String content, NotificationType type) throws ServiceException;
  
  /**
   * Send notification with specified content and type to user.
   * @param userId the user id
   * @param content the notification content
   * @param type the notification type
   * @return the notification id
   * @throws ServiceException on error
   */
  public int sendNotification(int userId, String content, NotificationType type) throws ServiceException;
  
  /**
   * Broadcast the notification.
   * @param title the notification title
   * @param content the notification content
   * @param type the notification type
   * @param expiration the expiration date
   * @return the notification id
   * @throws ServiceException on error
   */
  public int broadcastNotification(String title, String content, NotificationType type, Date expiration) throws ServiceException;
  
  /**
   * Mark a notification.
   * @param notificationId the notification id
   * @throws ServiceException on error
   */
  public void markNotification(int notificationId) throws ServiceException;
  
  /**
   * Unmark a notification.
   * @param notificationId the notification id
   * @throws ServiceException on error
   */
  public void unmarkNotification(int notificationId) throws ServiceException;
  
  /**
   * Read a notification.
   * @param notificationId the notification id
   * @throws ServiceException on error
   */
  public void readNotification(int notificationId) throws ServiceException;
  
  /**
   * Unread a notification.
   * @param notificationId the notification id
   * @throws ServiceException on error
   */
  public void unreadNotification(int notificationId) throws ServiceException;
  
  /**
   * Delete a notification.
   * @param notificationId the notification id
   * @throws ServiceException on error
   */
  public void deleteNotification(int notificationId) throws ServiceException;
}
