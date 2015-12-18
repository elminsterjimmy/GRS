package com.elminster.grs.web.service;

import java.util.List;

import com.elminster.grs.web.vo.response.CollectionInfo;
import com.elminster.web.commons.request.Option;

public interface GameCollectionService {
  
  public void triggerUpdateUserGameCollection(int userId);

  public List<CollectionInfo> getUsersGameCollectionInfo(int userId, Option options) throws CollectionException;
}
