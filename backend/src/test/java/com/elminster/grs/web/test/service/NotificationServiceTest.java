package com.elminster.grs.web.test.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.elminster.grs.shared.db.domain.Notification.NotificationType;
import com.elminster.grs.web.service.NotificationService;
import com.elminster.grs.web.service.ServiceErrorCode;
import com.elminster.grs.web.service.ServiceException;
import com.elminster.grs.web.test.TestBase;

public class NotificationServiceTest extends TestBase {
  
  @Autowired
  private NotificationService notificationService;

  @Test
  @Rollback
  @Transactional
  public void testFetchUserGameWorker() throws ServiceException {
    Assert.assertEquals(0, notificationService.getAllBroadcastNotifications().size());
    int nid1 = notificationService.sendNotification(1, "testNotification1", NotificationType.INFO);
    int nid2 = notificationService.sendNotification(1, "testNotification2", NotificationType.INFO);
    int nid3 = notificationService.sendNotification(1, "testNotification3", NotificationType.INFO);
    Assert.assertEquals(3, notificationService.getAllNotifications(1).size());
    notificationService.readNotification(nid1);
    notificationService.markNotification(nid2);
    Assert.assertEquals(3, notificationService.getAllNotifications(1).size());
    Assert.assertEquals(1, notificationService.getAllUnreadNotifications(1).size());
    Assert.assertEquals(1, notificationService.getAllMarkedNotifications(1).size());
    notificationService.deleteNotification(nid3);
    Assert.assertEquals(2, notificationService.getAllNotifications(1).size());
    try {
      notificationService.deleteNotification(nid2);
    } catch (ServiceException e) {
      Assert.assertEquals(ServiceErrorCode.DELETE_MARKED_NOTIFICATION, e.getErrorCode());
    }
    Assert.assertEquals(2, notificationService.getAllNotifications(1).size());
    notificationService.deleteNotification(nid1);
    Assert.assertEquals(1, notificationService.getAllNotifications(1).size());
    try {
      notificationService.readNotification(nid1);
    } catch (ServiceException e) {
      Assert.assertEquals(ServiceErrorCode.NOTIFCATION_ILLEGAL_STATUS, e.getErrorCode());
    }
  }

}
