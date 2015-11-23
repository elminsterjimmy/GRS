package com.elminster.grs.web.handler;

public interface UserInformationUpdater {

  public void updateUserInform(int userId) throws UserInformationUpdateException;
}
