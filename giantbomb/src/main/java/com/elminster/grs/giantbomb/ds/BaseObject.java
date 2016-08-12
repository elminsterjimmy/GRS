package com.elminster.grs.giantbomb.ds;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@MappedSuperclass
abstract public class BaseObject {
  
  @Column(name="gb_id")
  int gamebombId;
  @Column(length=1024)
  String name;
  @Column
  @Temporal(TemporalType.DATE)
  Date date_added;
  @Column
  @Temporal(TemporalType.DATE)
  Date date_last_updated;
  @Column(columnDefinition="text")
  String deck;
  @Column(columnDefinition="text")
  String description;
  @Column(length=1024)
  String gbApiUrl;
  @XmlTransient
  @Enumerated(EnumType.ORDINAL)
  @Column(nullable=false, length=1)
  GiantBombStatus status = GiantBombStatus.BASIC_INFO_CRAWLED;
  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
  @JoinColumn(name="image_id")
  GiantBombImage image;

  public void fulfill(BaseObject other) {
    if (null != other) {
      this.gamebombId = other.gamebombId;
      this.name = other.name;
      this.date_added = other.date_added;
      this.date_last_updated = other.date_last_updated;
      this.deck = other.deck;
      this.description = other.description;
      this.gbApiUrl = other.gbApiUrl;
      this.status = other.status;
      if (null != this.image) {
        this.image.fulfill(other.image);
      } else {
        this.image = other.image;
      }
    }
  }
  
  /**
   * @return the date_added
   */
  public Date getDate_added() {
    return date_added;
  }
  /**
   * @param date_added the date_added to set
   */
  public void setDate_added(Date date_added) {
    this.date_added = date_added;
  }
  /**
   * @return the date_last_updated
   */
  public Date getDate_last_updated() {
    return date_last_updated;
  }
  /**
   * @param date_last_updated the date_last_updated to set
   */
  public void setDate_last_updated(Date date_last_updated) {
    this.date_last_updated = date_last_updated;
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
   * @return the image
   */
  public GiantBombImage getImage() {
    return image;
  }
  /**
   * @param image the image to set
   */
  public void setImage(GiantBombImage image) {
    this.image = image;
  }
  /**
   * @return the name
   */
  public String getName() {
    return name;
  }
  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
  /**
   * @return the gbApiUrl
   */
  public String getGbApiUrl() {
    return gbApiUrl;
  }
  /**
   * @param gbApiUrl the gbApiUrl to set
   */
  @XmlElement(name = "api_detail_url")
  public void setGbApiUrl(String gbApiUrl) {
    this.gbApiUrl = gbApiUrl;
  }

  /**
   * @return the status
   */
  public GiantBombStatus getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(GiantBombStatus status) {
    this.status = status;
  }
}
