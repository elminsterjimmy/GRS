package com.elminster.grs.shared.db.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elminster.grs.shared.db.domain.Notification;
import com.elminster.grs.shared.db.domain.Notification.NotificationStatus;
import com.elminster.grs.shared.db.domain.Notification.NotificationType;

@Repository
public interface NotificationDao extends JpaRepository<Notification, Integer> {

  public List<Notification> findByOwner(int userId);
  
  public List<Notification> findByOwnerAndStatusIsNot(int userId, NotificationStatus status);
  
  public List<Notification> findByOwnerAndStatus(int userId, NotificationStatus status);
  
  public List<Notification> findByType(NotificationType type);
}
