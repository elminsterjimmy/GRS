package com.elminster.grs.web.service;

import java.util.List;

import com.elminster.grs.web.vo.response.CollectionInfo;
import com.elminster.web.commons.request.Option;

/**
 * The game collection service.
 * 
 * @author jgu
 * @version 1.0
 */
public interface GameCollectionService {

  public void triggerUpdateUserGameCollection(int userId);

  /**
   * Get user's game collections.
   * 
   * @param userId
   *          the user id who queries
   * @param username
   *          the username for querying the collections
   * @param options
   *          the options
   * @return the user's game collections
   */
  public List<CollectionInfo> getUsersGameCollections(int userId, String username, Option options)
      throws UserServiceException, CollectionException;
  
  public void addGameToFavorite(int userId, int userGameId, boolean favorite) throws CollectionException;
  
  public void rankGame(int userId, int userGameId, int ranking) throws CollectionException;
}
