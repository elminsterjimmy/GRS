package com.elminster.grs.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The user game meta entity.
 * 
 * @author jgu
 * @version 1.0
 */
@Entity
@Table(name = "users_game_meta")
public class UserGameMeta {

  @Id
  private Integer userId;
  @Column
  private String psnId;
  @Column
  private long psnTotalPoint;
  @Column
  private int psnLevel;
  @Column
  private int psnLevelProgress;
  @Column
  private int psnTotalBronze;
  @Column
  private int psnTotalSilver;
  @Column
  private int psnTotalGold;
  @Column
  private int psnTotalPlatinum;
  @Column
  private int psnTotalLevel;
  @Column
  private boolean psnIsPlusMember;
  @Column
  private String psnLocation;
  @Column
  private String psnBio;
  @Column
  private String psnRecentActive;
  /** the user's avatar url. **/
  @Column
  private String psnAvatarUrl;
  @Column
  private String liveId;
  @Column
  private int liveLevel;
  @Column
  private int liveLevelProgress;
  @Column
  private long liveTotalPoint;
  /** the user's reputation. **/
  @Column
  private String liveReputation;
  /** the user's location. **/
  @Column
  private String liveLocation;
  /** the user's bio . **/
  @Column
  private String liveBio;
  /** the user's recent active. **/
  @Column
  private String liveRecentActive;
  /** the user's avatar url. **/
  @Column
  private String liveAvatarUrl;

  /**
   * @return the userId
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * @param userId the userId to set
   */
  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  /**
   * @return the psnId
   */
  public String getPsnId() {
    return psnId;
  }

  /**
   * @param psnId the psnId to set
   */
  public void setPsnId(String psnId) {
    this.psnId = psnId;
  }

  /**
   * @return the liveId
   */
  public String getLiveId() {
    return liveId;
  }

  /**
   * @param liveId the liveId to set
   */
  public void setLiveId(String liveId) {
    this.liveId = liveId;
  }

  /**
   * @return the psnTotalPoint
   */
  public long getPsnTotalPoint() {
    return psnTotalPoint;
  }

  /**
   * @param psnTotalPoint the psnTotalPoint to set
   */
  public void setPsnTotalPoint(long psnTotalPoint) {
    this.psnTotalPoint = psnTotalPoint;
  }

  /**
   * @return the psnLevel
   */
  public int getPsnLevel() {
    return psnLevel;
  }

  /**
   * @param psnLevel the psnLevel to set
   */
  public void setPsnLevel(int psnLevel) {
    this.psnLevel = psnLevel;
  }

  /**
   * @return the psnLevelProgress
   */
  public int getPsnLevelProgress() {
    return psnLevelProgress;
  }

  /**
   * @param psnLevelProgress the psnLevelProgress to set
   */
  public void setPsnLevelProgress(int psnLevelProgress) {
    this.psnLevelProgress = psnLevelProgress;
  }

  /**
   * @return the psnTotalBronze
   */
  public int getPsnTotalBronze() {
    return psnTotalBronze;
  }

  /**
   * @param psnTotalBronze the psnTotalBronze to set
   */
  public void setPsnTotalBronze(int psnTotalBronze) {
    this.psnTotalBronze = psnTotalBronze;
  }

  /**
   * @return the psnTotalSilver
   */
  public int getPsnTotalSilver() {
    return psnTotalSilver;
  }

  /**
   * @param psnTotalSilver the psnTotalSilver to set
   */
  public void setPsnTotalSilver(int psnTotalSilver) {
    this.psnTotalSilver = psnTotalSilver;
  }

  /**
   * @return the psnTotalGold
   */
  public int getPsnTotalGold() {
    return psnTotalGold;
  }

  /**
   * @param psnTotalGold the psnTotalGold to set
   */
  public void setPsnTotalGold(int psnTotalGold) {
    this.psnTotalGold = psnTotalGold;
  }

  /**
   * @return the psnTotalPlatinum
   */
  public int getPsnTotalPlatinum() {
    return psnTotalPlatinum;
  }

  /**
   * @param psnTotalPlatinum the psnTotalPlatinum to set
   */
  public void setPsnTotalPlatinum(int psnTotalPlatinum) {
    this.psnTotalPlatinum = psnTotalPlatinum;
  }

  /**
   * @return the psnTotalLevel
   */
  public int getPsnTotalLevel() {
    return psnTotalLevel;
  }

  /**
   * @param psnTotalLevel the psnTotalLevel to set
   */
  public void setPsnTotalLevel(int psnTotalLevel) {
    this.psnTotalLevel = psnTotalLevel;
  }

  /**
   * @return the psnIsPlusMember
   */
  public boolean isPsnIsPlusMember() {
    return psnIsPlusMember;
  }

  /**
   * @param psnIsPlusMember the psnIsPlusMember to set
   */
  public void setPsnIsPlusMember(boolean psnIsPlusMember) {
    this.psnIsPlusMember = psnIsPlusMember;
  }

  /**
   * @return the psnLocation
   */
  public String getPsnLocation() {
    return psnLocation;
  }

  /**
   * @param psnLocation the psnLocation to set
   */
  public void setPsnLocation(String psnLocation) {
    this.psnLocation = psnLocation;
  }

  /**
   * @return the psnBio
   */
  public String getPsnBio() {
    return psnBio;
  }

  /**
   * @param psnBio the psnBio to set
   */
  public void setPsnBio(String psnBio) {
    this.psnBio = psnBio;
  }

  /**
   * @return the psnRecentActive
   */
  public String getPsnRecentActive() {
    return psnRecentActive;
  }

  /**
   * @param psnRecentActive the psnRecentActive to set
   */
  public void setPsnRecentActive(String psnRecentActive) {
    this.psnRecentActive = psnRecentActive;
  }

  /**
   * @return the psnAvatarUrl
   */
  public String getPsnAvatarUrl() {
    return psnAvatarUrl;
  }

  /**
   * @param psnAvatarUrl the psnAvatarUrl to set
   */
  public void setPsnAvatarUrl(String psnAvatarUrl) {
    this.psnAvatarUrl = psnAvatarUrl;
  }

  /**
   * @return the liveLevel
   */
  public int getLiveLevel() {
    return liveLevel;
  }

  /**
   * @param liveLevel the liveLevel to set
   */
  public void setLiveLevel(int liveLevel) {
    this.liveLevel = liveLevel;
  }

  /**
   * @return the liveLevelProgress
   */
  public int getLiveLevelProgress() {
    return liveLevelProgress;
  }

  /**
   * @param liveLevelProgress the liveLevelProgress to set
   */
  public void setLiveLevelProgress(int liveLevelProgress) {
    this.liveLevelProgress = liveLevelProgress;
  }

  /**
   * @return the liveTotalPoint
   */
  public long getLiveTotalPoint() {
    return liveTotalPoint;
  }

  /**
   * @param liveTotalPoint the liveTotalPoint to set
   */
  public void setLiveTotalPoint(long liveTotalPoint) {
    this.liveTotalPoint = liveTotalPoint;
  }

  /**
   * @return the liveReputation
   */
  public String getLiveReputation() {
    return liveReputation;
  }

  /**
   * @param liveReputation the liveReputation to set
   */
  public void setLiveReputation(String liveReputation) {
    this.liveReputation = liveReputation;
  }

  /**
   * @return the liveLocation
   */
  public String getLiveLocation() {
    return liveLocation;
  }

  /**
   * @param liveLocation the liveLocation to set
   */
  public void setLiveLocation(String liveLocation) {
    this.liveLocation = liveLocation;
  }

  /**
   * @return the liveBio
   */
  public String getLiveBio() {
    return liveBio;
  }

  /**
   * @param liveBio the liveBio to set
   */
  public void setLiveBio(String liveBio) {
    this.liveBio = liveBio;
  }

  /**
   * @return the liveRecentActive
   */
  public String getLiveRecentActive() {
    return liveRecentActive;
  }

  /**
   * @param liveRecentActive the liveRecentActive to set
   */
  public void setLiveRecentActive(String liveRecentActive) {
    this.liveRecentActive = liveRecentActive;
  }

  /**
   * @return the liveAvatarUrl
   */
  public String getLiveAvatarUrl() {
    return liveAvatarUrl;
  }

  /**
   * @param liveAvatarUrl the liveAvatarUrl to set
   */
  public void setLiveAvatarUrl(String liveAvatarUrl) {
    this.liveAvatarUrl = liveAvatarUrl;
  }
}
