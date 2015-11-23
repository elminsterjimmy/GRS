package com.elminster.grs.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "game_trophies_anchievements")
public class TrophyAndAchievement {

  //@formatter:off
  @Id
  @GeneratedValue(generator="id_gen")
  @GenericGenerator(name="id_gen", strategy="enhanced-table", 
  parameters = {
    @Parameter(name="table_name", value="game_sequence_id_gen"),
    @Parameter(name="value_column_name", value="next"), 
    @Parameter(name="segment_column_name",value="segment_name"), 
   @Parameter(name="segment_value", value="trophy_anchievement_seq"),
    @Parameter(name="increment_size", value="10"), 
    @Parameter(name="optimizer", value="pooled-lo") 
  })
  // @formatter:on
  private int id;
  @ManyToOne
  @JoinColumn(name="gameId")
  private Game game;
  @Column(nullable = false, length=255)
  private String trophyId;
  @Column(nullable = false, length=3)
  private int trophyOrder;
  @Column(nullable = false, length=1024)
  private String title;
  @Column(nullable = false, length=4)
  private int point = 0;
  @Column
  @Enumerated(EnumType.STRING)
  private TrophyType type; // UNKNOWN("Unknown", 0xFF),BRONZE("Bronze", 0x01),SILVER("Silver", 0x02),GOLD("Gold", 0x04),PLATINUM("Platinum", 0x08); 
  @Column(nullable = false, length=1024)
  private String description;
  @Column(length=1024)
  private String imageUrl;
  @Column(length=1) // 0: not updated (no one earned), 1: updated
  private int status = 0;
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
   * @return the game
   */
  public Game getGame() {
    return game;
  }
  /**
   * @param game the game to set
   */
  public void setGame(Game game) {
    this.game = game;
  }
  /**
   * @return the trophyId
   */
  public String getTrophyId() {
    return trophyId;
  }
  /**
   * @param trophyId the trophyId to set
   */
  public void setTrophyId(String trophyId) {
    this.trophyId = trophyId;
  }
  /**
   * @return the trophyOrder
   */
  public int getTrophyOrder() {
    return trophyOrder;
  }
  /**
   * @param trophyOrder the trophyOrder to set
   */
  public void setTrophyOrder(int trophyOrder) {
    this.trophyOrder = trophyOrder;
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
   * @return the point
   */
  public int getPoint() {
    return point;
  }
  /**
   * @param point the point to set
   */
  public void setPoint(int point) {
    this.point = point;
  }
  /**
   * @return the type
   */
  public TrophyType getType() {
    return type;
  }
  /**
   * @param type the type to set
   */
  public void setType(TrophyType type) {
    this.type = type;
  }
  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }
  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
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
