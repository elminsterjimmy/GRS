package com.elminster.grs.web.dxo.helper;

import java.util.List;

import com.elminster.common.constants.Constants.StringConstants;
import com.elminster.common.util.Assert;
import com.elminster.grs.web.domain.Game;
import com.elminster.grs.web.domain.Platform;
import com.elminster.grs.web.domain.UserGame;
import com.elminster.grs.web.response.vo.Achievements;
import com.elminster.grs.web.response.vo.CollectionInfo;
import com.elminster.grs.web.response.vo.Trophies;

public class CollectionInfoDxoHelper {

  public static CollectionInfo fillCollectionInfo(UserGame userGame) {
    Assert.notNull(userGame);
    CollectionInfo ci = new CollectionInfo();
    
    Game game = userGame.getGame();
    ci.setId(userGame.getId());
    ci.setCover(game.getImageUrl());
    ci.setFavorite(userGame.isFavorite());
    List<Platform> plts = game.getPlatform();
    StringBuilder sb = new StringBuilder(50);
    if (null != plts) {
      boolean first = true;
      for (Platform plt : plts) {
        if (first) {
          first = false;
        } else {
          sb.append(StringConstants.SPACE);
          sb.append(StringConstants.COMMA);
        }
        sb.append(plt.getPlatform());
      }
    }
    ci.setPlatform(sb.toString());
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
