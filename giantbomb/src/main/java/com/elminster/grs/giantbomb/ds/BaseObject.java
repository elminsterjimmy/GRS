package com.elminster.grs.giantbomb.ds;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;

@MappedSuperclass
abstract public class BaseObject {
  
  @Column(name="gb_id")
  int gamebombId;
  @Column
  String name;
  @Column
  @Temporal(TemporalType.DATE)
  Date date_added;
  @Column
  @Temporal(TemporalType.DATE)
  Date date_last_updated;
  @Column
  String deck;
  @Column
  String description;
  @OneToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
  @JoinColumn(name="image_id")
  GiantBombImage image;
  
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
}
