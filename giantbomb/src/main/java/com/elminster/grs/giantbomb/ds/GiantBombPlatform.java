package com.elminster.grs.giantbomb.ds;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/***
abbreviation Abbreviation of the platform.
api_detail_url  URL pointing to the platform detail resource.
company Company that created the platform.
date_added  Date the platform was added to Giant Bomb.
date_last_updated Date the platform was last updated on Giant Bomb.
deck  Brief summary of the platform.
description Description of the platform.
id  Unique ID of the platform.
image Main image of the platform.
install_base  Approximate number of sold hardware units.
name  Name of the platform.
online_support  Flag indicating whether the platform has online capabilities.
original_price  Initial price point of the platform.
release_date  Date of the platform.
site_detail_url URL pointing to the platform on Giant Bomb.
 */

@Entity
@Table(name="gaintbomb_platform")
public class GiantBombPlatform extends BaseObject implements CopyConstructor<GiantBombPlatform> {
  
  //@formatter:off
  @Id
  @Column(name="id")
  @GeneratedValue(generator="id_gen")
  @GenericGenerator(name="id_gen", strategy="enhanced-table", 
   parameters = {
     @Parameter(name="table_name", value="gaintbomb_game_seq_gen"),
     @Parameter(name="value_column_name", value="next"), 
     @Parameter(name="segment_column_name",value="seq_name"), 
     @Parameter(name="segment_value", value="platform_seq"),
     @Parameter(name="increment_size", value="1"), 
     @Parameter(name="optimizer", value="pooled-lo") 
   })
  // @formatter:on
  int internalId;
  @Column
  String abbreviation;
  @OneToOne
  @JoinColumn(name="company_id")
  GiantBombCompany company;
  @Column
  boolean online_support;
  @Column
  double original_price;
  @Column
  @Temporal(TemporalType.DATE)
  Date release_date;
  
  @Override
  public void fulfill(GiantBombPlatform other) {
    if (null != other) {
      super.fulfill(other);
      this.abbreviation = other.abbreviation;
      if (null != this.company) {
        this.company.fulfill(other.company);
      } else {
        this.company = other.company;
      }
      this.online_support = other.online_support;
      this.original_price = other.original_price;
      this.release_date = other.release_date;
    }
  }
  
  /**
   * @return the internalId
   */
  public int getInternalId() {
    return internalId;
  }
  /**
   * @param internalId the internalId to set
   */
  public void setInternalId(int internalId) {
    this.internalId = internalId;
  }
  /**
   * @return the abbreviation
   */
  public String getAbbreviation() {
    return abbreviation;
  }
  /**
   * @param abbreviation the abbreviation to set
   */
  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }
  /**
   * @return the company
   */
  public GiantBombCompany getCompany() {
    return company;
  }
  /**
   * @param company the company to set
   */
  public void setCompany(GiantBombCompany company) {
    this.company = company;
  }
  /**
   * @return the online_support
   */
  public boolean isOnline_support() {
    return online_support;
  }
  /**
   * @param online_support the online_support to set
   */
  public void setOnline_support(boolean online_support) {
    this.online_support = online_support;
  }
  /**
   * @return the original_price
   */
  public double getOriginal_price() {
    return original_price;
  }
  /**
   * @param original_price the original_price to set
   */
  public void setOriginal_price(double original_price) {
    this.original_price = original_price;
  }
  /**
   * @return the release_date
   */
  public Date getRelease_date() {
    return release_date;
  }
  /**
   * @param release_date the release_date to set
   */
  public void setRelease_date(Date release_date) {
    this.release_date = release_date;
  }
}
