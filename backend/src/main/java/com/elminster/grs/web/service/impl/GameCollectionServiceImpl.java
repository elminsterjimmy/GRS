package com.elminster.grs.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elminster.common.util.CollectionUtil;
import com.elminster.grs.web.dao.UserGameDao;
import com.elminster.grs.web.domain.UserGame;
import com.elminster.grs.web.dxo.helper.CollectionInfoDxoHelper;
import com.elminster.grs.web.response.vo.CollectionInfo;
import com.elminster.grs.web.service.CollectionException;
import com.elminster.grs.web.service.GameCollectionService;
import com.elminster.grs.web.util.OptionUtil;
import com.elminster.web.commons.request.Option;

/**
 * The game collection service.
 * 
 * @author jgu
 * @version 1.0
 */
@Service
public class GameCollectionServiceImpl implements GameCollectionService {
  
  @Autowired
  private UserGameDao userGameDao;
  
  /**
   * {@inheritDoc}
   */
  public void triggerUpdateUserGameCollection(int userId) {
    // ENHANCE may move to MQ
    
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<CollectionInfo> getUsersGameCollectionInfo(int userId, Option options) throws CollectionException {
    List<UserGame> userGames = userGameDao.findByUserId(userId, OptionUtil.getSort(options));
    triggerUpdateUserGameCollection(userId);
    List<CollectionInfo> collections = null;
    if (CollectionUtil.isNotEmpty(userGames)) {
      collections = new ArrayList<CollectionInfo>();
      for (UserGame userGame : userGames) {
        collections.add(CollectionInfoDxoHelper.fillCollectionInfo(userGame));
      }
    }
    return collections;
  }

}
