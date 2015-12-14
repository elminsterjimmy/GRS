package com.elminster.grs.shared.db.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "game_user_games")
public class UserGame {
  
  //@formatter:off
  @Id
  @GeneratedValue(generator="id_gen")
  @GenericGenerator(name="id_gen", strategy="enhanced-table", 
   parameters = {
     @Parameter(name="table_name", value="game_sequence_id_gen"),
     @Parameter(name="value_column_name", value="next"), 
     @Parameter(name="segment_column_name",value="segment_name"), 
     @Parameter(name="segment_value", value="user_game_seq"),
     @Parameter(name="increment_size", value="10"), 
     @Parameter(name="optimizer", value="pooled-lo") 
   })
  // @formatter:on
  private int id;
  
  @ManyToOne
  @JoinColumn(name="userId")
  private UserGameMeta user;
  
  @OneToOne
  @JoinColumn(name="gameId")
  private Game game;
  
  @Column(length=5)
  private int earnedPoint;
  
  @Column
  private boolean finished;
  
  @Column(length=3)
  private int earnedPlatinum;
  
  @Column(length=3)
  private int earnedGold;
  
  @Column(length=3)
  private int earnedSilver;
  
  @Column(length=3)
  private int earnedBrozen;
  
  @Column(length=3)
  private int progress;
  
  @Column(length=2)
  private int rating;
  
  @Column
  private boolean favorite;
  
  @Column
  private int status;
  
  @OneToMany(mappedBy="userGame")
  private Set<UserTrophyAndAchievement> trophiesAchievements;
  
  @Column(updatable=false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date addedDate;
  
  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastPlayedDate;
  
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
   * @param game the game to set
   */
  public void setGame(Game game) {
    this.game = game;
  }

  /**
   * @return the earnedPoint
   */
  public int getEarnedPoint() {
    return earnedPoint;
  }

  /**
   * @param earnedPoint the earnedPoint to set
   */
  public void setEarnedPoint(int earnedPoint) {
    this.earnedPoint = earnedPoint;
  }

  /**
   * @return the earnedGold
   */
  public int getEarnedGold() {
    return earnedGold;
  }

  /**
   * @param earnedGold the earnedGold to set
   */
  public void setEarnedGold(int earnedGold) {
    this.earnedGold = earnedGold;
  }

  /**
   * @return the earnedSilver
   */
  public int getEarnedSilver() {
    return earnedSilver;
  }

  /**
   * @param earnedSilver the earnedSilver to set
   */
  public void setEarnedSilver(int earnedSilver) {
    this.earnedSilver = earnedSilver;
  }

  /**
   * @return the earnedBrozen
   */
  public int getEarnedBrozen() {
    return earnedBrozen;
  }

  /**
   * @param earnedBrozen the earnedBrozen to set
   */
  public void setEarnedBrozen(int earnedBrozen) {
    this.earnedBrozen = earnedBrozen;
  }

  /**
   * @return the progress
   */
  public int getProgress() {
    return progress;
  }

  /**
   * @param progress the progress to set
   */
  public void setProgress(int progress) {
    this.progress = progress;
  }

  /**
   * @return the rating
   */
  public int getRating() {
    return rating;
  }

  /**
   * @param rating the rating to set
   */
  public void setRating(int rating) {
    this.rating = rating;
  }

  /**
   * @return the favorite
   */
  public boolean isFavorite() {
    return favorite;
  }

  /**
   * @param favorite the favorite to set
   */
  public void setFavorite(boolean favorite) {
    this.favorite = favorite;
  }

  /**
   * @return the lastPlayedDate
   */
  public Date getLastPlayedDate() {
    return lastPlayedDate;
  }

  /**
   * @param lastPlayedDate the lastPlayedDate to set
   */
  public void setLastPlayedDate(Date lastPlayedDate) {
    this.lastPlayedDate = lastPlayedDate;
  }

  /**
   * @return the finished
   */
  public boolean isFinished() {
    return finished;
  }

  /**
   * @param finished the finished to set
   */
  public void setFinished(boolean finished) {
    this.finished = finished;
  }

  /**
   * @return the enrnedPlatinum
   */
  public int getEarnedPlatinum() {
    return earnedPlatinum;
  }

  /**
   * @param earnedPlatinum the earnedPlatinum to set
   */
  public void setEarnedPlatinum(int earnedPlatinum) {
    this.earnedPlatinum = earnedPlatinum;
  }

  /**
   * @return the addedDate
   */
  public Date getAddedDate() {
    return addedDate;
  }

  /**
   * @param addedDate the addedDate to set
   */
  public void setAddedDate(Date addedDate) {
    this.addedDate = addedDate;
  }

  /**
   * @return the game
   */
  public Game getGame() {
    return game;
  }

  /**
   * @return the trophiesAchievements
   */
  public Set<UserTrophyAndAchievement> getTrophiesAchievements() {
    return trophiesAchievements;
  }

  /**
   * @param trophiesAchievements the trophiesAchievements to set
   */
  public void setTrophiesAchievements(Set<UserTrophyAndAchievement> trophiesAchievements) {
    this.trophiesAchievements = trophiesAchievements;
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
}
