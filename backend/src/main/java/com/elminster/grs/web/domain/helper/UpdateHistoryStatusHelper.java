package com.elminster.grs.web.domain.helper;

import com.elminster.common.util.BinaryUtil;

/**
 * The helper class for update history status.
 * 
 * @author jgu
 * @version 1.0
 */
final public class UpdateHistoryStatusHelper {
  
  private static final int UPDATE_PSN_PROFILE_SUCCESS_BIT = 0;
  private static final int UPDATE_PSN_PROFILE_FAILED_BIT = 1;
  private static final int UPDATED_PSN_PROFILE = 0x0F;
  private static final int UPDATE_PSN_PROFILE_BINARY = ~UPDATED_PSN_PROFILE;
  private static final int UPDATE_PSN_TROPHIES_SUCCESS_BIT = 4;
  private static final int UPDATE_PSN_TROPHIES_FAILED_BIT = 5;
  private static final int UPDATED_PSN_TROPHIES = 0xF0;
  private static final int UPDATE_PSN_TROPHIES_BINARY = ~UPDATED_PSN_TROPHIES;
  private static final int UPDATE_LIVE_PROFILE_SUCCESS_BIT = 8;
  private static final int UPDATE_LIVE_PROFILE_FAILED_BIT = 9;
  private static final int UPDATED_LIVE_PROFILE = 0xF00;
  private static final int UPDATED_LIVE_PROFILE_BINARY = ~UPDATED_LIVE_PROFILE;
  private static final int UPDATE_LIVE_ACHIEVEMENTS_SUCCESS_BIT = 12;
  private static final int UPDATE_LIVE_ACHIEVEMENTS_FAILED_BIT = 13;
  private static final int UPDATED_LIVE_ACHIEVEMENTS = 0xF000;
  private static final int UPDATED_LIVE_ACHIEVEMENTS_BINARY = ~UPDATED_LIVE_ACHIEVEMENTS;

  public static int updatePsnProfileSuccess(final int status) {
    return BinaryUtil.setBit(status & UPDATE_PSN_PROFILE_BINARY, UPDATE_PSN_PROFILE_SUCCESS_BIT);
  }
  
  public static boolean isUpdatePsnProfileSuccess(final int status) {
    return BinaryUtil.isSet(status, UPDATE_PSN_PROFILE_SUCCESS_BIT);
  }
  
  public static int updatePsnProfileFailed(final int status) {
    return BinaryUtil.setBit(status & UPDATE_PSN_PROFILE_BINARY, UPDATE_PSN_PROFILE_FAILED_BIT);
  }
  
  public static boolean isPsnProfileUpdated(final int status) {
    return (status & UPDATED_PSN_PROFILE) > 0;
  }
  
  public static int updatePsnTrophiesSuccess(final int status) {
    return BinaryUtil.setBit(status & UPDATE_PSN_TROPHIES_BINARY, UPDATE_PSN_TROPHIES_SUCCESS_BIT);
  }
  
  public static boolean isUpdatePsnTrophiesSuccess(final int status) {
    return BinaryUtil.isSet(status, UPDATE_PSN_TROPHIES_SUCCESS_BIT);
  }
  
  public static int updatePsnTrophiesFailed(final int status) {
    return BinaryUtil.setBit(status & UPDATE_PSN_TROPHIES_BINARY, UPDATE_PSN_TROPHIES_FAILED_BIT);
  }
  
  public static boolean isPsnTrophiesUpdated(final int status) {
    return (status & UPDATED_PSN_TROPHIES) > 0;
  }
  
  public static int updateLiveProfileSuccess(final int status) {
    return BinaryUtil.setBit(status & UPDATED_LIVE_PROFILE_BINARY, UPDATE_LIVE_PROFILE_SUCCESS_BIT);
  }
  
  public static boolean isUpdateLiveProfileSuccess(final int status) {
    return BinaryUtil.isSet(status, UPDATE_LIVE_PROFILE_SUCCESS_BIT);
  }
  
  public static int updateLiveProfileFailed(final int status) {
    return BinaryUtil.setBit(status & UPDATED_LIVE_PROFILE_BINARY, UPDATE_LIVE_PROFILE_FAILED_BIT);
  }
  
  public static boolean isLiveProfileUpdated(final int status) {
    return (status & UPDATED_LIVE_PROFILE) > 0;
  }
  
  public static int updateLiveAchievementsSuccess(final int status) {
    return BinaryUtil.setBit(status & UPDATED_LIVE_ACHIEVEMENTS_BINARY, UPDATE_LIVE_ACHIEVEMENTS_SUCCESS_BIT);
  }
  
  public static boolean isUpdateLiveAchievementsSuccess(final int status) {
    return BinaryUtil.isSet(status, UPDATE_LIVE_ACHIEVEMENTS_SUCCESS_BIT);
  }
  
  public static int updateLiveAchievementsFailed(final int status) {
    return BinaryUtil.setBit(status & UPDATED_LIVE_ACHIEVEMENTS_BINARY, UPDATE_LIVE_ACHIEVEMENTS_FAILED_BIT);
  }
  
  public static boolean isLiveAchievementsUpdated(final int status) {
    return (status & UPDATED_LIVE_ACHIEVEMENTS) > 0;
  }
}
