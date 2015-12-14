package com.elminster.grs.crawler.updater.impl;

import com.elminster.grs.crawler.updater.GameInformationUpdater;
import com.elminster.grs.crawler.updater.InformationUpdateException;
import com.elminster.retrieve.web.data.Response;

public class GamespotGameInformationUpdater extends HttpInformationUpdater implements GameInformationUpdater<Response> {

  @Override
  public void updateInformation(Response source) throws InformationUpdateException {
    
  }
}
