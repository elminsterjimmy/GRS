package com.elminster.grs.giantbomb.ds;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * 
 *Fields
abbreviation  Abbreviation of the company.
aliases List of aliases the company is known by. A \n (newline) seperates each alias.
api_detail_url  URL pointing to the company detail resource.
characters  Characters related to the company.
concepts  Concepts related to the company.
date_added  Date the company was added to Giant Bomb.
date_founded  Date the company was founded.
date_last_updated Date the company was last updated on Giant Bomb.
deck  Brief summary of the company.
description Description of the company.
developed_games Games the company has developed.
developer_releases  Releases the company has developed.
distributor_releases  Releases the company has distributed.
id  Unique ID of the company.
image Main image of the company.
location_address  Street address of the company.
location_city City the company resides in.
location_country  Country the company resides in.
location_address  Street address of the company.
location_city City the company resides in.
location_country  Country the company resides in.
location_state  State the company resides in.
locations Locations related to the company.
name  Name of the company.
objects Objects related to the company.
people  People who have worked with the company.
phone Phone number of the company.
published_games Games published by the company.
publisher_releases  Releases the company has published.
site_detail_url URL pointing to the company on Giant Bomb.
website URL to the company website.
 */
@Entity
@Table(name="gaintbomb_company")
public class SingleCompany extends BaseObject {
  
  //@formatter:off
  @Id
  @Column(name="id")
  @GeneratedValue(generator="id_gen")
  @GenericGenerator(name="id_gen", strategy="enhanced-table", 
   parameters = {
     @Parameter(name="table_name", value="gaintbomb_game_seq_gen"),
     @Parameter(name="value_column_name", value="next"), 
     @Parameter(name="segment_column_name",value="seq_name"), 
     @Parameter(name="segment_value", value="compan_seq"),
     @Parameter(name="increment_size", value="10"), 
     @Parameter(name="optimizer", value="pooled-lo") 
   })
  // @formatter:on
  int internalId;
  @Column
  String abbreviation;
  @Column
  String aliases;
  @Column
  @Temporal(TemporalType.DATE)
  Date date_founded;
  @Column
  String website;
  
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
   * @return the aliases
   */
  public String getAliases() {
    return aliases;
  }
  /**
   * @param aliases the aliases to set
   */
  public void setAliases(String aliases) {
    this.aliases = aliases;
  }
  /**
   * @return the date_founded
   */
  public Date getDate_founded() {
    return date_founded;
  }
  /**
   * @param date_founded the date_founded to set
   */
  public void setDate_founded(Date date_founded) {
    this.date_founded = date_founded;
  }
  /**
   * @return the website
   */
  public String getWebsite() {
    return website;
  }
  /**
   * @param website the website to set
   */
  public void setWebsite(String website) {
    this.website = website;
  }
}
