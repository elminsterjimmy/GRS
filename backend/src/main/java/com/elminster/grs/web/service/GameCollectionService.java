package com.elminster.grs.web.service;

import java.util.List;

import com.elminster.grs.web.domain.UserEx;
import com.elminster.grs.web.response.vo.CollectionInfo;
import com.elminster.web.commons.request.Option;

public interface GameCollectionService {
  
  public void triggerUpdateUserGameCollection(int userId);

  public List<CollectionInfo> getUsersGameCollectionInfo(int userId, Option options) throws CollectionException;
}
