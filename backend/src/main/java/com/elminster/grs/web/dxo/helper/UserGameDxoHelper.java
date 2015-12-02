package com.elminster.grs.web.dxo.helper;

import com.elminster.common.util.Assert;
import com.elminster.grs.web.domain.Game;
import com.elminster.grs.web.domain.UserGame;
import com.elminster.grs.web.domain.UserGameMeta;
import com.elminster.retrieve.data.user.PSNUserGame;
import com.elminster.retrieve.data.user.PSNUserProfile;

final public class UserGameDxoHelper {

  public static final UserGame fillUserGame(UserGame userGame, PSNUserGame psnUserGame) {
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
  
  public static final Game fillGame(Game game, PSNUserGame psnUserGame) {
    Assert.notNull(game, "Game cannot be null.");
    Assert.notNull(psnUserGame, "PSN User Game cannot be null.");
    game.setGameInternalId(psnUserGame.getGameId());
    game.setPlatinumCount(psnUserGame.getPlatinumCount());
    game.setGlodCount(psnUserGame.getGoldCount());
    game.setSilverCount(psnUserGame.getSilverCount());
    game.setBronzeCount(psnUserGame.getBronzeCount());
    game.setTitle(psnUserGame.getTitle());
    game.setImageUrl(psnUserGame.getImageUrl());
    return game;
  }
  
  public static final UserGameMeta fillUserGameMeta(UserGameMeta userGameMeta, PSNUserProfile psnUserProfile) {
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
