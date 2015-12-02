package com.elminster.grs.web.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.elminster.grs.web.dao.UserGameMetaDao;
import com.elminster.grs.web.domain.UserGameMeta;
import com.elminster.grs.web.service.GameInfoUpdateService;
import com.elminster.grs.web.service.ServiceException;
import com.elminster.grs.web.test.TestBase;

public class GameInfoUpdateServiceTest extends TestBase {
  
  @Autowired
  private GameInfoUpdateService gameInfoUpdateService;
  @Autowired
  private UserGameMetaDao dao;

  @Test
  //@Rollback
  //@Transactional
  //@ExpectedException(NotCodeException.class)  
  public void testFetchUserGameWorker() throws ServiceException {
    gameInfoUpdateService.updateUserGame(1);
  }
}
