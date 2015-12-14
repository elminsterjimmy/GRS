package com.elminster.grs.shared.db.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "game_user_psn_games")
public class UserPsnGame extends UserGame {

  //@formatter:off
  @Id
  @GeneratedValue(generator="id_gen")
  @GenericGenerator(name="id_gen", strategy="enhanced-table", 
   parameters = {
     @Parameter(name="table_name", value="game_sequence_id_gen"),
     @Parameter(name="value_column_name", value="next"), 
     @Parameter(name="segment_column_name",value="segment_name"), 
     @Parameter(name="segment_value", value="user_psn_game_seq"),
     @Parameter(name="increment_size", value="10"), 
     @Parameter(name="optimizer", value="pooled-lo") 
   })
  // @formatter:on
  private int id;
  
  @Column(length=3)
  private int earnedPlatinum;
  
  @Column(length=3)
  private int earnedGold;
  
  @Column(length=3)
  private int earnedSilver;
  
  @Column(length=3)
  private int earnedBrozen;
  
//  @OneToMany(mappedBy="userGame")
//  private Set<UserTrophy> trophies;

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
   * @return the earnedPlatinum
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

//  /**
//   * @return the trophies
//   */
//  public Set<UserTrophy> getTrophies() {
//    return trophies;
//  }
//
//  /**
//   * @param trophies the trophies to set
//   */
//  public void setTrophies(Set<UserTrophy> trophies) {
//    this.trophies = trophies;
//  }
}
