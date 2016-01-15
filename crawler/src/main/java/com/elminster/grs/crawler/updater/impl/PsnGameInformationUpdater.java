package com.elminster.grs.crawler.updater.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elminster.common.util.CollectionUtil;
import com.elminster.grs.crawler.dxo.helper.UserGameDxoHelper;
import com.elminster.grs.crawler.impl.CrawlerErrorCode;
import com.elminster.grs.crawler.updater.GameInformationUpdater;
import com.elminster.grs.crawler.updater.InformationUpdateException;
import com.elminster.grs.shared.db.dao.GameDao;
import com.elminster.grs.shared.db.dao.TrophyAndAchieveDao;
import com.elminster.grs.shared.db.dao.UserGameDao;
import com.elminster.grs.shared.db.dao.UserGameMetaDao;
import com.elminster.grs.shared.db.dao.UserTrophyAndAchieveDao;
import com.elminster.grs.shared.db.domain.Game;
import com.elminster.grs.shared.db.domain.TrophyAndAchievement;
import com.elminster.grs.shared.db.domain.TrophyType;
import com.elminster.grs.shared.db.domain.UserGame;
import com.elminster.grs.shared.db.domain.UserGameMeta;
import com.elminster.grs.shared.db.domain.UserTrophyAndAchievement;
import com.elminster.retrieve.psn.data.user.PSNUserGame;
import com.elminster.retrieve.psn.data.user.PSNUserProfile;
import com.elminster.retrieve.psn.data.user.PSNUserTrophy;
import com.elminster.retrieve.psn.exception.ServiceException;
import com.elminster.retrieve.psn.service.IPSNApi;
import com.elminster.retrieve.psn.service.PSNApiImpl;

/**
 * PSN Game information updater updates the PSN profile and game list for certain user if the user's psn id is not
 * empty.
 * 
 * @author jgu
 * @version 1.0
 */
@Service("PsnGameInformationUpdater")
@Transactional
public class PsnGameInformationUpdater extends DatabaseInformationUpdater<Integer> implements
    GameInformationUpdater<Integer> {

  @Autowired
  private UserGameMetaDao userGameMetaDao;
  @Autowired
  private UserGameDao userGameDao;
  @Autowired
  private GameDao gameDao;
  @Autowired
  private TrophyAndAchieveDao tnaDao;
  @Autowired
  private UserTrophyAndAchieveDao userTnaDao;
  /** the psn api. */
  private IPSNApi psnApi = new PSNApiImpl();

  /**
   * {@inheritDoc}
   */
  @Override
  public void updateInformation(Integer userId) throws InformationUpdateException {
    UserGameMeta userGameMeta = userGameMetaDao.findOne(userId);
    if (null != userGameMeta) {
      String psnId = userGameMeta.getPsnId();
      if (null != psnId) {
        PSNUserProfile psnUserProfile;
        List<PSNUserGame> psnUserGames;
        try {
          psnUserProfile = psnApi.getPSNUserProfile(psnId);
        } catch (ServiceException se) {
          throw new InformationUpdateException(CrawlerErrorCode.RETRIEVE_PSN_PROFILE_EXCEPTION, se);
        }
        try {
          psnUserGames = psnApi.getPSNUserGameList(psnId);
        } catch (ServiceException se) {
          throw new InformationUpdateException(CrawlerErrorCode.RETRIEVE_PSN_GAME_LIST_EXCEPTION, se);
        }
        try {
          UserGameDxoHelper.fillUserPsnGameMeta(userGameMeta, psnUserProfile);
          if (null != psnUserGames) {
            List<UserGame> userGames = fillPsnUserGames(userGameMeta, psnUserGames);
            for (UserGame userGame : userGames) {
              List<PSNUserTrophy> psnUserTrophies = psnApi.getPSNUserGameTrophies(psnId, userGame.getGame()
                  .getPsnInternalId());
              fillPsnUserTrophies(userGame, psnUserTrophies);
            }
          }
        } catch (Exception e) {
          throw new InformationUpdateException(CrawlerErrorCode.UPDATE_PSN_INFORMATION_EXCEPTION, e);
        }
      }
    }
  }

  private void fillPsnUserTrophies(UserGame userGame, List<PSNUserTrophy> psnUserTrophies) {
    if (null != psnUserTrophies) {
      List<UserTrophyAndAchievement> userTnas = userTnaDao.findByUserGame(userGame);
      if (null == userTnas) {
        // no trophy or achievement available yet.
        userTnas = new ArrayList<UserTrophyAndAchievement>(psnUserTrophies.size());
      }
      if (CollectionUtil.isNotEmpty(psnUserTrophies)) {
        int length = psnUserTrophies.size();
        int curLength = userTnas.size();
        for (int i = 0; i < length; i++) {
          UserTrophyAndAchievement utna;
          if (i >= curLength) {
            // could happen if no trophy info or the trophy is increased.
            utna = new UserTrophyAndAchievement();
            utna.setUser(userGame.getUser());
            utna.setUserGame(userGame);
            userTnas.add(utna);
          } else {
            utna = userTnas.get(i);
          }
          fillTrophy(utna, psnUserTrophies.get(i), i);
        }
        userTnaDao.save(userTnas);
      }
    }
  }

  public void fillTrophy(UserTrophyAndAchievement utna, PSNUserTrophy psnUserTrophy, int order) {
    TrophyAndAchievement tna = utna.getTrophyAndAchieve();
    if (null == tna) {
      Game game = utna.getUserGame().getGame();
      tna = tnaDao.findByGameIdAndTrophyId(game.getId(), psnUserTrophy.getTrophyId());
      if (null == tna) {
        // trophy not found
        tna = new TrophyAndAchievement();
        tna.setGame(game);
      }
    }
    int status = tna.getStatus();
    tna.setDescription(psnUserTrophy.getDescription());
    tna.setPoint(psnUserTrophy.getPoint());
    tna.setTitle(psnUserTrophy.getTitle());
    tna.setTrophyId(psnUserTrophy.getTrophyId());
    tna.setTrophyOrder(order);
    tna.setType(TrophyType.getTrophyType(psnUserTrophy.getType().getType()));
    utna.setTrophyAndAchieve(tna);
    boolean unlocked = psnUserTrophy.isEarned();
    utna.setUnlocked(unlocked);
    if (unlocked) {
      // only updated if unlocked.
      utna.setUnlockedDate(psnUserTrophy.getEarnedDate());
      tna.setImageUrl(psnUserTrophy.getImageUrl());
      status = 1;
    }
    tna.setStatus(status);
  }

  private List<UserGame> fillPsnUserGames(UserGameMeta userGameMeta, List<PSNUserGame> psnUserGames) {
    List<UserGame> userGames = null;
    if (null != psnUserGames) {
      int userId = userGameMeta.getUserId();
      // the list to update
      userGames = new ArrayList<>();
      for (PSNUserGame psnUserGame : psnUserGames) {
        UserGame userGame = userGameDao.findByUserIdAndGameInternalId(userId, psnUserGame.getGameId());
        Game game;
        if (null == userGame) {
          // to be create
          game = gameDao.findByPsnInternalId(psnUserGame.getGameId());
          if (null == game) {
            // game not available
            game = new Game();
          }
          userGame = new UserGame();
          UserGameDxoHelper.fillUserPsnGame(userGame, psnUserGame);
          UserGameDxoHelper.fillPsnGame(game, psnUserGame);
          userGame.setGame(game);
          userGame.setUser(userGameMeta);
        } else {
          // to be update
          game = userGame.getGame();
          UserGameDxoHelper.fillUserPsnGame(userGame, psnUserGame);
          UserGameDxoHelper.fillPsnGame(game, psnUserGame);
        }
        userGames.add(userGame);
      }
      // update all user games
      userGameDao.save(userGames);
    }
    return userGames;
  }
}
