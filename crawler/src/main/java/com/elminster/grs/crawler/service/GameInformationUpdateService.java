package com.elminster.grs.crawler.service;

public interface GameInformationUpdateService {

  public void updateGameInformation(Integer userId) throws GameInformationUpdateException;
  
  public void setUpdateThreshold(long thresholdInMs);
}
