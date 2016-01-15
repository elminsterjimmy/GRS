package com.elminster.grs.crawler.dxo.helper;

import com.elminster.common.util.Assert;
import com.elminster.grs.shared.db.domain.Game;
import com.elminster.grs.shared.db.domain.UserGame;
import com.elminster.grs.shared.db.domain.UserGameMeta;
import com.elminster.retrieve.psn.data.user.PSNUserGame;
import com.elminster.retrieve.psn.data.user.PSNUserProfile;

final public class UserGameDxoHelper {

  public static final UserGame fillUserPsnGame(UserGame userGame, PSNUserGame psnUserGame) {
    Assert.notNull(userGame, "User Game cannot be null.");
    Assert.notNull(psnUserGame, "PSN User Game cannot be null.");
    userGame.setProgress(psnUserGame.getCompletionByPercent());
    userGame.setEarnedPlatinum(psnUserGame.getEarnedPlatinum());
    userGame.setEarnedGold(psnUserGame.getEarnedGold());
    userGame.setEarnedSilver(psnUserGame.getEarnedSilver());
    userGame.setEarnedBrozen(psnUserGame.getEarnedBronze());
    userGame.setEarnedPoint(psnUserGame.getEarnedPoint());
    return userGame;
  }
  
  public static final Game fillPsnGame(Game game, PSNUserGame psnUserGame) {
    Assert.notNull(game, "Game cannot be null.");
    Assert.notNull(psnUserGame, "PSN User Game cannot be null.");
    game.setPsnInternalId(psnUserGame.getGameId());
    game.setPlatinumCount(psnUserGame.getPlatinumCount());
    game.setGlodCount(psnUserGame.getGoldCount());
    game.setSilverCount(psnUserGame.getSilverCount());
    game.setBronzeCount(psnUserGame.getBronzeCount());
    game.setTitle(psnUserGame.getTitle());
    game.setImageUrl(psnUserGame.getImageUrl());
    return game;
  }
  
  public static final UserGameMeta fillUserPsnGameMeta(UserGameMeta userGameMeta, PSNUserProfile psnUserProfile) {
    Assert.notNull(userGameMeta, "User Game Meta cannot be null.");
    if (null != psnUserProfile) {
      userGameMeta.setPsnBio(psnUserProfile.getBio());
      userGameMeta.setPsnLevel(psnUserProfile.getLevel());
      userGameMeta.setLiveLevelProgress(psnUserProfile.getLevelProcess());
      userGameMeta.setPsnLocation(psnUserProfile.getLocation());
      userGameMeta.setPsnRecentActive(psnUserProfile.getRecentActive());
      userGameMeta.setPsnTotalBronze(psnUserProfile.getTotalBronze());
      userGameMeta.setPsnTotalGold(psnUserProfile.getTotalGold());
      userGameMeta.setPsnTotalLevel(psnUserProfile.getTotalLevel());
      userGameMeta.setPsnTotalPlatinum(psnUserProfile.getTotalPlatinum());
      userGameMeta.setPsnTotalPoint(psnUserProfile.getTotalPoint());
      userGameMeta.setPsnTotalSilver(psnUserProfile.getTotalSilver());
      userGameMeta.setPsnAvatarUrl(psnUserProfile.getUserAvatarUrl());
    }
    return userGameMeta;
  }
}
