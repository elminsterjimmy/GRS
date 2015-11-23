package com.elminster.grs.web.runnable;

import org.springframework.beans.factory.annotation.Autowired;

import com.elminster.grs.web.service.GameInfoUpdateService;
import com.elminster.grs.web.service.ServiceException;

public class FetchUserGameWorker implements Runnable {

  @Autowired
  private GameInfoUpdateService gameInfoUpdateService;
  private int userId;
  
  public FetchUserGameWorker(int userId) {
    this.userId = userId;
  }

  @Override
  public void run() {
    try {
      gameInfoUpdateService.updateUserGame(userId);
    } catch (ServiceException e) {
      // TODO
      e.printStackTrace();
    }
  }
}
