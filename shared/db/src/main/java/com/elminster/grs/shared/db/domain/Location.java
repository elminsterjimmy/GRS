package com.elminster.grs.shared.db.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "comm_locations")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Location {

  @Id
  private Integer id;
  @Column(nullable=false, length=50)
  private String name;
  @ManyToOne()
  @JoinColumn(name="parentId")
  private Location parent;
  @Column(nullable=false, length=50)
  private String shortName;
  @Column(length=6)
  private Integer code;
  @Column(length=10)
  private Integer zipCode;
  @Column(length=100)
  private String pinyin;
  @Column(length=20)
  private String lng;
  @Column(length=20)
  private String lat;
  @Column(nullable=false, length=1)
  private Integer level;
  @Column(nullable=false, length=255)
  private String position;
  @Column(length=3)
  private int sort;
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
   * @return the parent
   */
  public Location getParent() {
    return parent;
  }
  /**
   * @param parent the parent to set
   */
  public void setParent(Location parent) {
    this.parent = parent;
  }
  /**
   * @return the shortName
   */
  public String getShortName() {
    return shortName;
  }
  /**
   * @param shortName the shortName to set
   */
  public void setShortName(String shortName) {
    this.shortName = shortName;
  }
  /**
   * @return the code
   */
  public int getCode() {
    return code;
  }
  /**
   * @param code the code to set
   */
  public void setCode(int code) {
    this.code = code;
  }
  /**
   * @return the zipCode
   */
  public int getZipCode() {
    return zipCode;
  }
  /**
   * @param zipCode the zipCode to set
   */
  public void setZipCode(int zipCode) {
    this.zipCode = zipCode;
  }
  /**
   * @return the pinyin
   */
  public String getPinyin() {
    return pinyin;
  }
  /**
   * @param pinyin the pinyin to set
   */
  public void setPinyin(String pinyin) {
    this.pinyin = pinyin;
  }
  /**
   * @return the lng
   */
  public String getLng() {
    return lng;
  }
  /**
   * @param lng the lng to set
   */
  public void setLng(String lng) {
    this.lng = lng;
  }
  /**
   * @return the lat
   */
  public String getLat() {
    return lat;
  }
  /**
   * @param lat the lat to set
   */
  public void setLat(String lat) {
    this.lat = lat;
  }
  /**
   * @return the level
   */
  public int getLevel() {
    return level;
  }
  /**
   * @param level the level to set
   */
  public void setLevel(int level) {
    this.level = level;
  }
  /**
   * @return the position
   */
  public String getPosition() {
    return position;
  }
  /**
   * @param position the position to set
   */
  public void setPosition(String position) {
    this.position = position;
  }
  /**
   * @return the sort
   */
  public int getSort() {
    return sort;
  }
  /**
   * @param sort the sort to set
   */
  public void setSort(int sort) {
    this.sort = sort;
  }
}
