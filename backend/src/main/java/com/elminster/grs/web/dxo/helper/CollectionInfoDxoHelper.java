package com.elminster.grs.web.dxo.helper;

import com.elminster.common.util.Assert;
import com.elminster.grs.shared.db.domain.Game;
import com.elminster.grs.shared.db.domain.UserGame;
import com.elminster.grs.web.vo.response.Achievements;
import com.elminster.grs.web.vo.response.CollectionInfo;
import com.elminster.grs.web.vo.response.Trophies;

public class CollectionInfoDxoHelper {

  public static CollectionInfo fillCollectionInfo(UserGame userGame) {
    Assert.notNull(userGame);
    CollectionInfo ci = new CollectionInfo();
    
    Game game = userGame.getGame();
    ci.setId(userGame.getId());
    ci.setCover(game.getImageUrl());
    ci.setFavorite(userGame.isFavorite());
    ci.setProgress(userGame.getProgress());
    ci.setRating(userGame.getRating());
    ci.setTitle(game.getTitle());
    
    Trophies trophies = new Trophies();
    trophies.setBrozen(userGame.getEarnedBrozen());
    trophies.setGold(userGame.getEarnedGold());
    trophies.setPlatinum(userGame.getEarnedPlatinum());
    trophies.setSilver(userGame.getEarnedSilver());
    
    Achievements achievements = new Achievements();
    achievements.setEnaredPoints(userGame.getEarnedPoint());
    achievements.setTotalPoints(game.getTotalPoint());
    
    ci.setTrophies(trophies);
    ci.setAchievements(achievements);
    return ci;
  }
}
