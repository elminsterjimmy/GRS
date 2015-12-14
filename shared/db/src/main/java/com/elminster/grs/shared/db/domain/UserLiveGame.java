package com.elminster.grs.shared.db.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.OneToMany;

public class UserLiveGame extends UserGame {

  @Column(length=5)
  private int earnedPoint;
  
  @OneToMany(mappedBy="userGame")
  private Set<Achievement> achievements;

  /**
   * @return the earnedPoint
   */
  public int getEarnedPoint() {
    return earnedPoint;
  }

  /**
   * @param earnedPoint the earnedPoint to set
   */
  public void setEarnedPoint(int earnedPoint) {
    this.earnedPoint = earnedPoint;
  }

  /**
   * @return the achievements
   */
  public Set<Achievement> getAchievements() {
    return achievements;
  }

  /**
   * @param achievements the achievements to set
   */
  public void setAchievements(Set<Achievement> achievements) {
    this.achievements = achievements;
  }
}
