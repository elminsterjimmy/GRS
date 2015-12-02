package com.elminster.grs.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "game_update_history")
public class GameInfoUpdateHistory {
  
  //@formatter:off
  @Id
  @GeneratedValue(generator="id_gen")
  @GenericGenerator(name="id_gen", strategy="enhanced-table", 
   parameters = {
     @Parameter(name="table_name", value="game_sequence_id_gen"),
     @Parameter(name="value_column_name", value="next"), 
     @Parameter(name="segment_column_name",value="segment_name"), 
     @Parameter(name="segment_value", value="update_history_seq"),
     @Parameter(name="increment_size", value="10"),
     @Parameter(name="optimizer", value="pooled-lo") 
   })
  // @formatter:on
  private int id;
  
  @Column
  private int userId;
  
  @Column
  private int status;
  
  @Column(length=8)
  private String errorCode;
  
  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastUpdate;
  
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
   * @return the userId
   */
  public int getUserId() {
    return userId;
  }

  /**
   * @param userId the userId to set
   */
  public void setUserId(int userId) {
    this.userId = userId;
  }
  
  /**
   * @return the status
   */
  public int getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(int status) {
    this.status = status;
  }

  /**
   * @return the lastUpdate
   */
  public Date getLastUpdate() {
    return lastUpdate;
  }

  /**
   * @param lastUpdate the lastUpdate to set
   */
  public void setLastUpdate(Date lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  /**
   * @return the errorCode
   */
  public String getErrorCode() {
    return errorCode;
  }

  /**
   * @param errorCode the errorCode to set
   */
  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }
}
