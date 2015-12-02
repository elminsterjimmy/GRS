package com.elminster.grs.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * The notification entity.
 * 
 * @author jgu
 * @version 1.0
 */
@Entity
@Table(name = "comm_notification")
public class Notification {
  
  //@formatter:off
  @Id
  @GeneratedValue(generator="id_gen")
  @GenericGenerator(name="id_gen", strategy="enhanced-table", 
   parameters = {
     @Parameter(name="table_name", value="message_sequence_id_gen"),
     @Parameter(name="value_column_name", value="next"), 
     @Parameter(name="segment_column_name",value="segment_name"), 
     @Parameter(name="segment_value", value="notification_seq"),
     @Parameter(name="increment_size", value="10"),
     @Parameter(name="optimizer", value="pooled-lo") 
   })
  // @formatter:on
  private int id;
  @Column
  private int owner;
  @Column(length=1024)
  private String title;
  @Column(length=4096)
  private String content;
  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;
  @Enumerated(EnumType.ORDINAL)
  @Column(nullable=false, length=1)
  private NotificationType type = NotificationType.INFO;
  @Enumerated(EnumType.ORDINAL)
  @Column(nullable=false, length=1)
  private NotificationStatus status = NotificationStatus.UNREAD;
  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private Date expiration;
  
  public Notification() {
    super();
  }
  
  public Notification(int owner, String title, String content, NotificationType type) {
    this.owner = owner;
    this.title = title;
    this.content = content;
    this.type = type;
    this.status = NotificationStatus.UNREAD;
    this.createdDate = new Date(System.currentTimeMillis());
  }

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the owner
   */
  public int getOwner() {
    return owner;
  }

  /**
   * @param owner the owner to set
   */
  public void setOwner(int owner) {
    this.owner = owner;
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return the content
   */
  public String getContent() {
    return content;
  }

  /**
   * @param content the content to set
   */
  public void setContent(String content) {
    this.content = content;
  }

  /**
   * @return the createdDate
   */
  public Date getCreatedDate() {
    return createdDate;
  }

  /**
   * @param createdDate the createdDate to set
   */
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  /**
   * @return the type
   */
  public NotificationType getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(NotificationType type) {
    this.type = type;
  }

  /**
   * @return the status
   */
  public NotificationStatus getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(NotificationStatus status) {
    this.status = status;
  }

  /**
   * @return the expiration
   */
  public Date getExpiration() {
    return expiration;
  }

  /**
   * @param expiration the expiration to set
   */
  public void setExpiration(Date expiration) {
    this.expiration = expiration;
  }

  public static enum NotificationType {
    INFO("Info"),
    WARNNING("Warnning");
    
    String value;
    
    NotificationType(String value) {
      this.value = value;
    }
    
    public String getValue() {
      return value;
    }
  }
  
  public static enum NotificationStatus {
    UNREAD,
    READ,
    MARKED,
    DELETED,
    BROADCAST;
  }
}
