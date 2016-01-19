package com.elminster.grs.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elminster.common.util.CollectionUtil;
import com.elminster.grs.shared.db.dao.UserGameDao;
import com.elminster.grs.shared.db.domain.UserGame;
import com.elminster.grs.web.dxo.helper.CollectionInfoDxoHelper;
import com.elminster.grs.web.service.CollectionException;
import com.elminster.grs.web.service.GameCollectionService;
import com.elminster.grs.web.service.ServiceErrorCode;
import com.elminster.grs.web.service.UserService;
import com.elminster.grs.web.service.UserServiceException;
import com.elminster.grs.web.util.OptionUtil;
import com.elminster.grs.web.vo.response.CollectionInfo;
import com.elminster.spring.security.domain.User;
import com.elminster.web.commons.request.Option;

/**
 * The game collection service.
 * 
 * @author jgu
 * @version 1.0
 */
@Service
public class GameCollectionServiceImpl implements GameCollectionService {

  /** the logger. */
  private static final Log logger = LogFactory.getLog(GameCollectionServiceImpl.class);

  @Autowired
  private UserGameDao userGameDao;
  @Autowired
  private UserService userService;

  /**
   * {@inheritDoc}
   */
  public void triggerUpdateUserGameCollection(int userId) {
    // TODO call enqueue in MQ
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<CollectionInfo> getUsersGameCollections(int userId, String username, Option options)
      throws UserServiceException, CollectionException {
    logger.info(String.format("User [%d] trying to get user [%s]'s game collections.", userId, username));
    User user = userService.getUserByUsername(username);
    List<CollectionInfo> collections = null;
    if (user.getId() == userId) {
      // call by self
      List<UserGame> userGames = userGameDao.findByUserId(user.getId(), OptionUtil.getSort(options));
      triggerUpdateUserGameCollection(user.getId());
      if (CollectionUtil.isNotEmpty(userGames)) {
        collections = new ArrayList<CollectionInfo>();
        for (UserGame userGame : userGames) {
          collections.add(CollectionInfoDxoHelper.fillCollectionInfo(userGame));
        }
      }
    } else {
      // TODO check visibility
    }
    return collections;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Transactional
  public void addGameToFavorite(int userId, int userGameId, boolean favorite) throws CollectionException {
    logger.info(String.format("User [%d] trying to add game [%d] into favorite [%b].", userId, userGameId, favorite));
    UserGame userGame = userGameDao.findOne(userGameId);
    if (userId != userGame.getUser().getUserId()) {
      throw new CollectionException(ServiceErrorCode.COLLECTION_ILLEGAL_STATUS, String.format(
          "Favorite an unmatched game [%d] with user [%d].", userGameId, userId));
    }
    userGame.setFavorite(favorite);
    userGameDao.save(userGame);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Transactional
  public void rankGame(int userId, int userGameId, int ranking) throws CollectionException {
    logger.info(String.format("User [%d] trying to rank game [%d] with rank [%d].", userId, userGameId, ranking));
    UserGame userGame = userGameDao.findOne(userGameId);
    if (userId != userGame.getUser().getUserId()) {
      throw new CollectionException(ServiceErrorCode.COLLECTION_ILLEGAL_STATUS, String.format(
          "Ranking an unmatched game [%d] with user [%d].", userGameId, userId));
    }
    userGame.setRating(ranking);
    userGameDao.save(userGame);
  }

}
