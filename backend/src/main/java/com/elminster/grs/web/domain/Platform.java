package com.elminster.grs.web.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "game_platforms")
public class Platform {

  //@formatter:off
  @Id
  @GeneratedValue(generator="id_gen")
  @GenericGenerator(name="id_gen", strategy="enhanced-table", 
   parameters = {
     @Parameter(name="table_name", value="game_sequence_id_gen"),
     @Parameter(name="value_column_name", value="next"), 
     @Parameter(name="segment_column_name",value="segment_name"), 
     @Parameter(name="segment_value", value="platform_seq"),
     @Parameter(name="increment_size", value="10"), 
     @Parameter(name="optimizer", value="pooled-lo") 
   })
  // @formatter:on
  private int id;
  @Column(nullable = false, length=255)
  private String platform;
  @ManyToMany(mappedBy="platform", fetch = FetchType.LAZY)
  private List<Game> games;
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
   * @return the platform
   */
  public String getPlatform() {
    return platform;
  }
  /**
   * @param platform the platform to set
   */
  public void setPlatform(String platform) {
    this.platform = platform;
  }
  /**
   * @return the games
   */
  public List<Game> getGames() {
    return games;
  }
  /**
   * @param games the games to set
   */
  public void setGames(List<Game> games) {
    this.games = games;
  }
}
