package com.elminster.grs.giantbomb.ds;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * api_detail_url URL pointing to the video detail resource.
deck  Brief summary of the video.
hd_url  URL to the HD version of the video. 
This URL will not be usable from a web-browser.
You must use our embed player to play our videos on any other web site. See embed_player below. 
For apps you must have a registered User Agent to load and play our videos. Contact edgework@giantbomb.com to do that
high_url  URL to the High Res version of the video. 
This URL will not be usable from a web-browser.
You must use our embed player to play our videos on any other web site. See embed_player below. 
For apps you must have a registered User Agent to load and play our videos. Contact edgework@giantbomb.com to do that
low_url URL to the Low Res version of the video. 
This URL will not be usable from a web-browser.
You must use our embed player to play our videos on any other web site. See embed_player below. 
For apps you must have a registered User Agent to load and play our videos. Contact edgework@giantbomb.com to do that
embed_player  URL for video embed player. To be inserted into an iFrame. You can add ?autoplay=true to auto-play. See http://www.giantbomb.com/api/video-embed-sample/ for more information on using the embed player.
id  Unique ID of the video.
image Main image of the video.
length_seconds  Length (in seconds) of the video.
name  Name of the video.
publish_date  Date the video was published on Giant Bomb.
site_detail_url URL pointing to the video on Giant Bomb.
url The video's filename.
user  Author of the video.
youtube_id  Youtube ID for the video.
 */
@Entity
@Table(name="gaintbomb_video")
public class GiantBombVideo extends BaseObject implements CopyConstructor<GiantBombVideo> {

  //@formatter:off
  @Id
  @Column(name="id")
  @GeneratedValue(generator="id_gen")
  @GenericGenerator(name="id_gen", strategy="enhanced-table", 
   parameters = {
     @Parameter(name="table_name", value="gaintbomb_game_seq_gen"),
     @Parameter(name="value_column_name", value="next"), 
     @Parameter(name="segment_column_name",value="seq_name"), 
     @Parameter(name="segment_value", value="video_seq"),
     @Parameter(name="increment_size", value="1"), 
     @Parameter(name="optimizer", value="pooled-lo") 
   })
  // @formatter:on
  int InternalId;
  @Column(name="gb_id")
  int gamebombId;
  @Column(length=2048)
  String deck;
  @Column(length=1024)
  String hd_url;
  @Column(length=1024)
  String high_url;
  @Column(length=1024)
  String low_url;
  @Column(length=1024)
  String embed_player;
  @Column
  long length_seconds;
  @Column
  @Temporal(TemporalType.DATE)
  Date publish_date;
  @Column(length=1024)
  String url;
  @Column
  long youtube_id;
  
  @Override
  public void fulfill(GiantBombVideo other) {
    if (null != other) {
      this.deck = other.deck;
      this.embed_player = other.embed_player;
      this.gamebombId = other.gamebombId;
      this.hd_url = other.hd_url;
      this.high_url = other.high_url;
      this.length_seconds = other.length_seconds;
      this.low_url = other.low_url;
      this.publish_date = other.publish_date;
      this.url = other.url;
      this.youtube_id = other.youtube_id;
    }
  }
  
  /**
   * @return the internalId
   */
  public int getInternalId() {
    return InternalId;
  }
  /**
   * @param internalId the internalId to set
   */
  public void setInternalId(int internalId) {
    InternalId = internalId;
  }
  /**
   * @return the gamebombId
   */
  public int getGamebombId() {
    return gamebombId;
  }
  /**
   * @param gamebombId the gamebombId to set
   */
  @XmlElement(name="id")
  public void setGamebombId(int gamebombId) {
    this.gamebombId = gamebombId;
  }
  /**
   * @return the deck
   */
  public String getDeck() {
    return deck;
  }
  /**
   * @param deck the deck to set
   */
  public void setDeck(String deck) {
    this.deck = deck;
  }
  /**
   * @return the hd_url
   */
  public String getHd_url() {
    return hd_url;
  }
  /**
   * @param hd_url the hd_url to set
   */
  public void setHd_url(String hd_url) {
    this.hd_url = hd_url;
  }
  /**
   * @return the high_url
   */
  public String getHigh_url() {
    return high_url;
  }
  /**
   * @param high_url the high_url to set
   */
  public void setHigh_url(String high_url) {
    this.high_url = high_url;
  }
  /**
   * @return the low_url
   */
  public String getLow_url() {
    return low_url;
  }
  /**
   * @param low_url the low_url to set
   */
  public void setLow_url(String low_url) {
    this.low_url = low_url;
  }
  /**
   * @return the embed_player
   */
  public String getEmbed_player() {
    return embed_player;
  }
  /**
   * @param embed_player the embed_player to set
   */
  public void setEmbed_player(String embed_player) {
    this.embed_player = embed_player;
  }
  /**
   * @return the length_seconds
   */
  public long getLength_seconds() {
    return length_seconds;
  }
  /**
   * @param length_seconds the length_seconds to set
   */
  public void setLength_seconds(long length_seconds) {
    this.length_seconds = length_seconds;
  }
  /**
   * @return the publish_date
   */
  public Date getPublish_date() {
    return publish_date;
  }
  /**
   * @param publish_date the publish_date to set
   */
  public void setPublish_date(Date publish_date) {
    this.publish_date = publish_date;
  }
  /**
   * @return the url
   */
  public String getUrl() {
    return url;
  }
  /**
   * @param url the url to set
   */
  public void setUrl(String url) {
    this.url = url;
  }
  /**
   * @return the youtube_id
   */
  public long getYoutube_id() {
    return youtube_id;
  }
  /**
   * @param youtube_id the youtube_id to set
   */
  public void setYoutube_id(long youtube_id) {
    this.youtube_id = youtube_id;
  }
}
