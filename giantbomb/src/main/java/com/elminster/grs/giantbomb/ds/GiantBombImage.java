package com.elminster.grs.giantbomb.ds;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="gaintbomb_image")
public class GiantBombImage {

  //@formatter:off
  @Id
  @Column(name="id")
  @GeneratedValue(generator="id_gen")
  @GenericGenerator(name="id_gen", strategy="enhanced-table", 
   parameters = {
     @Parameter(name="table_name", value="gaintbomb_game_seq_gen"),
     @Parameter(name="value_column_name", value="next"), 
     @Parameter(name="segment_column_name",value="seq_name"), 
     @Parameter(name="segment_value", value="image_seq"),
     @Parameter(name="increment_size", value="1"), 
     @Parameter(name="optimizer", value="pooled-lo") 
   })
  // @formatter:on
  int id;
  @Column
  String icon_url;
  @Column
  String medium_url;
  @Column
  String screen_url;
  @Column
  String small_url;
  @Column
  String super_url;
  @Column
  String thumb_url;
  @Column
  String tiny_url;
  @Column
  String tags;
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
   * @return the icon_url
   */
  public String getIcon_url() {
    return icon_url;
  }
  /**
   * @param icon_url the icon_url to set
   */
  public void setIcon_url(String icon_url) {
    this.icon_url = icon_url;
  }
  /**
   * @return the medium_url
   */
  public String getMedium_url() {
    return medium_url;
  }
  /**
   * @param medium_url the medium_url to set
   */
  public void setMedium_url(String medium_url) {
    this.medium_url = medium_url;
  }
  /**
   * @return the screen_url
   */
  public String getScreen_url() {
    return screen_url;
  }
  /**
   * @param screen_url the screen_url to set
   */
  public void setScreen_url(String screen_url) {
    this.screen_url = screen_url;
  }
  /**
   * @return the small_url
   */
  public String getSmall_url() {
    return small_url;
  }
  /**
   * @param small_url the small_url to set
   */
  public void setSmall_url(String small_url) {
    this.small_url = small_url;
  }
  /**
   * @return the super_url
   */
  public String getSuper_url() {
    return super_url;
  }
  /**
   * @param super_url the super_url to set
   */
  public void setSuper_url(String super_url) {
    this.super_url = super_url;
  }
  /**
   * @return the thumb_url
   */
  public String getThumb_url() {
    return thumb_url;
  }
  /**
   * @param thumb_url the thumb_url to set
   */
  public void setThumb_url(String thumb_url) {
    this.thumb_url = thumb_url;
  }
  /**
   * @return the tiny_url
   */
  public String getTiny_url() {
    return tiny_url;
  }
  /**
   * @param tiny_url the tiny_url to set
   */
  public void setTiny_url(String tiny_url) {
    this.tiny_url = tiny_url;
  }
  /**
   * @return the tags
   */
  public String getTags() {
    return tags;
  }
  /**
   * @param tags the tags to set
   */
  public void setTags(String tags) {
    this.tags = tags;
  }
}
