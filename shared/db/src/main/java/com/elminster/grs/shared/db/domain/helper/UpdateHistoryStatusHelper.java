package com.elminster.grs.shared.db.domain.helper;

import com.elminster.grs.shared.db.domain.GameInfoUpdateHistory.UpdateStatus;

/**
 * The helper class for update history status.
 * 
 * @author jgu
 * @version 1.0
 */
final public class UpdateHistoryStatusHelper {

  public static boolean isPsnProfileUpdated(UpdateStatus status) {
    return status == UpdateStatus.UPDATE_PSN_PROFILE_SUCCESS || status == UpdateStatus.UPDATE_PSN_PROFILE_FAILED;
  }
  
}
