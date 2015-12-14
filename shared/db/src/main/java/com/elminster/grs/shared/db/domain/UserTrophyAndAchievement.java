package com.elminster.grs.shared.db.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "game_user_trophies_achievements")
public class UserTrophyAndAchievement {
  
  //@formatter:off
  @Id
  @GeneratedValue(generator="id_gen")
  @GenericGenerator(name="id_gen", strategy="enhanced-table", 
   parameters = {
     @Parameter(name="table_name", value="game_sequence_id_gen"),
     @Parameter(name="value_column_name", value="next"), 
     @Parameter(name="segment_column_name",value="segment_name"), 
     @Parameter(name="segment_value", value="user_trophy_achievement_seq"),
     @Parameter(name="increment_size", value="50"), 
     @Parameter(name="optimizer", value="pooled-lo") 
   })
  // @formatter:on
  private int id;
  
  @ManyToOne
  @JoinColumn(name="userId")
  private UserGameMeta user;
  
  @ManyToOne
  @JoinColumn(name="userGameId")
  private UserGame userGame;
  
  @OneToOne
  @JoinColumn(name="trophyAchieveId")
  private TrophyAndAchievement trophyAndAchieve;
  
  @Column
  private boolean unlocked;
  
  @Column(updatable=false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date unlockedDate;

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
   * @return the user
   */
  public UserGameMeta getUser() {
    return user;
  }

  /**
   * @param user the user to set
   */
  public void setUser(UserGameMeta user) {
    this.user = user;
  }

  /**
   * @return the userGame
   */
  public UserGame getUserGame() {
    return userGame;
  }

  /**
   * @param userGame the userGame to set
   */
  public void setUserGame(UserGame userGame) {
    this.userGame = userGame;
  }

  /**
   * @return the trophyAndAchieve
   */
  public TrophyAndAchievement getTrophyAndAchieve() {
    return trophyAndAchieve;
  }

  /**
   * @param trophyAndAchieve the trophyAndAchieve to set
   */
  public void setTrophyAndAchieve(TrophyAndAchievement trophyAndAchieve) {
    this.trophyAndAchieve = trophyAndAchieve;
  }

  /**
   * @return the unlocked
   */
  public boolean isUnlocked() {
    return unlocked;
  }

  /**
   * @param unlocked the unlocked to set
   */
  public void setUnlocked(boolean unlocked) {
    this.unlocked = unlocked;
  }

  /**
   * @return the unlockedDate
   */
  public Date getUnlockedDate() {
    return unlockedDate;
  }

  /**
   * @param unlockedDate the unlockedDate to set
   */
  public void setUnlockedDate(Date unlockedDate) {
    this.unlockedDate = unlockedDate;
  }
}
