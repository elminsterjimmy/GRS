package com.elminster.grs.crawler.updater;

public interface InformationUpdater<T> {

  public void updateInformation(T source) throws InformationUpdateException;
}
