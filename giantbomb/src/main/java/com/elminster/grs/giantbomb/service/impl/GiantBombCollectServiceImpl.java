package com.elminster.grs.giantbomb.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.elminster.common.constants.Constants.StringConstants;
import com.elminster.common.constants.FileExtensionConstants;
import com.elminster.common.util.FileUtil;
import com.elminster.common.util.IOUtil;
import com.elminster.grs.giantbomb.config.Config;
import com.elminster.grs.giantbomb.ds.BaseObject;
import com.elminster.grs.giantbomb.ds.GiantBombCompany;
import com.elminster.grs.giantbomb.ds.GiantBombGame;
import com.elminster.grs.giantbomb.ds.GiantBombGenre;
import com.elminster.grs.giantbomb.ds.GiantBombPlatform;
import com.elminster.grs.giantbomb.ds.GiantBombTheme;
import com.elminster.grs.giantbomb.ds.GiantBombVideo;
import com.elminster.grs.giantbomb.service.CollectConf;
import com.elminster.grs.giantbomb.service.GiantBombCollectException;
import com.elminster.grs.giantbomb.service.GiantBombCollectService;
import com.elminster.grs.giantbomb.service.InternalFS;
import com.elminster.grs.giantbomb.util.ApiCounter;
import com.elminster.retrieve.web.DefaultWebRetriever;
import com.elminster.retrieve.web.data.Method;
import com.elminster.retrieve.web.data.Response;

/**
 * The service that crawl the information from GiantBomb.
 * 
 * @author jgu
 * @version 1.0
 */
@Component
public class GiantBombCollectServiceImpl implements GiantBombCollectService, InternalFS {

  /** the logger. */
  private static final Log logger = LogFactory.getLog(GiantBombCollectServiceImpl.class);

  private static final String REQUEST_API_KEY = Config.INSTANCE.getStringProperty("REQUEST_API_KEY");

  @Override
  public void collectDetailGameInfo(CollectConf conf, GiantBombGame game) throws GiantBombCollectException {
    collectDetailInformation(conf, CRAWLED_INDIVIDUAL_GAME_FOLDER, game.getInternalId(), game);
  }
  
  @Override
  public void collectDetailPlatformInfo(CollectConf conf, GiantBombPlatform platform) throws GiantBombCollectException {
    collectDetailInformation(conf, CRAWLED_INDIVIDUAL_PLATFORM_FOLDER, platform.getInternalId(), platform);
  }
  
  @Override
  public void collectDetailGenreInfo(CollectConf conf, GiantBombGenre platform) throws GiantBombCollectException {
    collectDetailInformation(conf, CRAWLED_INDIVIDUAL_GENRE_FOLDER, platform.getInternalId(), platform);
  }

  @Override
  public void collectDetailThemeInfo(CollectConf conf, GiantBombTheme platform) throws GiantBombCollectException {
    collectDetailInformation(conf, CRAWLED_INDIVIDUAL_THEME_FOLDER, platform.getInternalId(), platform);
  }

  @Override
  public void collectDetailCompanyInfo(CollectConf conf, GiantBombCompany platform) throws GiantBombCollectException {
    collectDetailInformation(conf, CRAWLED_INDIVIDUAL_COMPANY_FOLDER, platform.getInternalId(), platform);
  }
  
  @Override
  public void collectDetailVideoInfo(CollectConf conf, GiantBombVideo video) throws GiantBombCollectException {
    collectDetailInformation(conf, CRAWLED_INDIVIDUAL_VIDEO_FOLDER, video.getInternalId(), video);
  }
 
  private void collectDetailInformation(CollectConf conf, String crawledToDirectory, int internalId, BaseObject baseObject) throws GiantBombCollectException {
    String crawlFileName = new StringBuilder()
        .append(internalId)
        .append(StringConstants.UNDER_LINE)
        .append(FileUtil.toSafeFileName(baseObject.getName()))
        .append(StringConstants.UNDER_LINE)
        .append(baseObject.getGamebombId())
        .append(FileExtensionConstants.XML_EXTENSION).toString();
    String fullName = crawledToDirectory + crawlFileName;
    File f = new File(fullName);
    if (f.exists()) {
      logger.info(String.format("Already crawled object [%s] in file [%s].", baseObject.getName(), fullName));
      return;
    }

    ApiCounter apiCounter = conf.getApiCounter();
    String endpoint = String.format(baseObject.getGbApiUrl() + REQUEST_API_KEY, apiCounter.getApiKey());
    apiCounter.acquire();
    DefaultWebRetriever retriever = new DefaultWebRetriever(endpoint, Method.GET_METHOD);
    try {
      Response response = retriever.retrieve();
      InputStream is = response.getBodyAsInputStream();
      FileUtil.createFolder(fullName);
      try (FileWriter fw = new FileWriter(f)) {
        IOUtil.copy(is, fw);
      } catch (IOException e) {
        throw e;
      }
    } catch (Exception e) {
      throw new GiantBombCollectException(e);
    }
  }
 }
