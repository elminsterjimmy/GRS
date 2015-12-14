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
@Table(name = "game_games")
public class Game {

  //@formatter:off
  @Id
  @GeneratedValue(generator="id_gen")
  @GenericGenerator(name="id_gen", strategy="enhanced-table", 
   parameters = {
     @Parameter(name="table_name", value="game_sequence_id_gen"),
     @Parameter(name="value_column_name", value="next"), 
     @Parameter(name="segment_column_name",value="segment_name"), 
     @Parameter(name="segment_value", value="game_seq"),
     @Parameter(name="increment_size", value="10"), 
     @Parameter(name="optimizer", value="pooled-lo") 
   })
   // @formatter:on
  private int id;
  @Column(nullable = false, length=255)
  private String internalId;
  @Column(nullable = false, length=1024)
  private String title;
  @Column(nullable = false, length=4)
  private int totalPoint = 0;
  @Column(nullable = false, length=3)
  private int bronzeCount = 0;
  @Column(nullable = false, length=3)
  private int silverCount = 0;
  @Column(nullable = false, length=3)
  private int glodCount = 0;
  @Column(nullable = false, length=3)
  private int platinumCount = 0;
  @Column(nullable = false, length=3)
  private int totalCount = 0;
  @Column(length=1024)
  private String imageUrl;
  @OneToMany(mappedBy="game")
  private Set<TrophyAndAchievement> trophiesAndAchievements;
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
   * @return the totalPoint
   */
  public int getTotalPoint() {
    return totalPoint;
  }
  /**
   * @param totalPoint the totalPoint to set
   */
  public void setTotalPoint(int totalPoint) {
    this.totalPoint = totalPoint;
  }
  /**
   * @return the bronzeCount
   */
  public int getBronzeCount() {
    return bronzeCount;
  }
  /**
   * @param bronzeCount the bronzeCount to set
   */
  public void setBronzeCount(int bronzeCount) {
    this.bronzeCount = bronzeCount;
  }
  /**
   * @return the silverCount
   */
  public int getSilverCount() {
    return silverCount;
  }
  /**
   * @param silverCount the silverCount to set
   */
  public void setSilverCount(int silverCount) {
    this.silverCount = silverCount;
  }
  /**
   * @return the glodCount
   */
  public int getGlodCount() {
    return glodCount;
  }
  /**
   * @param glodCount the glodCount to set
   */
  public void setGlodCount(int glodCount) {
    this.glodCount = glodCount;
  }
  /**
   * @return the platinumCount
   */
  public int getPlatinumCount() {
    return platinumCount;
  }
  /**
   * @param platinumCount the platinumCount to set
   */
  public void setPlatinumCount(int platinumCount) {
    this.platinumCount = platinumCount;
  }
  /**
   * @return the totalCount
   */
  public int getTotalCount() {
    return totalCount;
  }
  /**
   * @param totalCount the totalCount to set
   */
  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }
  /**
   * @return the imageUrl
   */
  public String getImageUrl() {
    return imageUrl;
  }
  /**
   * @param imageUrl the imageUrl to set
   */
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
  /**
   * @return the trophiesAndAchievements
   */
  public Set<TrophyAndAchievement> getTrophiesAndAchievements() {
    return trophiesAndAchievements;
  }
  /**
   * @param trophiesAndAchievements the trophiesAndAchievements to set
   */
  public void setTrophiesAndAchievements(Set<TrophyAndAchievement> trophiesAndAchievements) {
    this.trophiesAndAchievements = trophiesAndAchievements;
  }
  /**
   * @param gameInternalId the gameInternalId to set
   */
  public void setGameInternalId(String gameInternalId) {
    this.internalId = gameInternalId;
  }
  /**
   * @return the gameInternalId
   */
  public String getGameInternalId() {
    return internalId;
  }
}
