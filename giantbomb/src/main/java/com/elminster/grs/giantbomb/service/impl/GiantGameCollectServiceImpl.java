package com.elminster.grs.giantbomb.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.elminster.common.constants.Constants.StringConstants;
import com.elminster.common.constants.FileExtensionConstants;
import com.elminster.common.pool.ThreadPool;
import com.elminster.common.util.DateUtil;
import com.elminster.common.util.FileUtil;
import com.elminster.common.util.IOUtil;
import com.elminster.grs.giantbomb.config.Config;
import com.elminster.grs.giantbomb.config.CrawlJobId;
import com.elminster.grs.giantbomb.ds.GiantBombGame;
import com.elminster.grs.giantbomb.service.GaintGameService;
import com.elminster.grs.giantbomb.service.GameCollectConf;
import com.elminster.grs.giantbomb.service.GameCollectException;
import com.elminster.grs.giantbomb.service.GiantGameCollectService;
import com.elminster.grs.giantbomb.service.InternalFS;
import com.elminster.grs.giantbomb.service.job.CollectBasicGamesJob;
import com.elminster.grs.giantbomb.service.job.CollectDetailGameInfoJob;
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
public class GiantGameCollectServiceImpl implements GiantGameCollectService, InternalFS {

  /** the logger. */
  private static final Log logger = LogFactory.getLog(GiantGameCollectServiceImpl.class);

  public static final long SCHEDULE_FIXED_RATE = DateUtil.HOUR;

  private static final String API_BASE = Config.INSTANCE.getStringProperty("API_BASE");
  private static final String PS3_PLATFORM_ID = Config.INSTANCE.getStringProperty("PS3_PLATFORM_ID");
  private static final String PS4_PLATFORM_ID = Config.INSTANCE.getStringProperty("PS4_PLATFORM_ID");
  private static final String GAMES_ENDPOINT = Config.INSTANCE.getStringProperty("GAMES_ENDPOINT");
  private static final String PLATFORM_FILTER = Config.INSTANCE.getStringProperty("PLATFORM_FILTER");
  private static final String REQUEST_API_KEY = Config.INSTANCE.getStringProperty("REQUEST_API_KEY");

  /**
   * the game service.
   */
  @Autowired
  private GaintGameService gameService;

  @Override
  public void collectBasicGameInfo(GameCollectConf conf) {
    collectPs3GameInfo(conf);
    collectPs4GameInfo(conf);
  }

  @Override
  public void collectDetailGameInfo(GameCollectConf conf) {
    CollectDetailGameInfoJob job = new CollectDetailGameInfoJob(
        CrawlJobId.COLLECT_DETAIL_GAME_INFO_JOB_ID,
        "Collect detail game information",
        conf,
        this,
        gameService);
    ThreadPool.getThreadPool().execute(job);
  }

  @Override
  public void collectDetailGameInfo(GameCollectConf conf, GiantBombGame game) throws GameCollectException {
    String crawlFileName = new StringBuilder()
        .append(game.getInternalId())
        .append(StringConstants.UNDER_LINE)
        .append(FileUtil.toSafeFileName(game.getName()))
        .append(StringConstants.UNDER_LINE)
        .append(game.getGamebombId())
        .append(FileExtensionConstants.XML_EXTENSION).toString();
    String fullName = CRAWLED_INDIVIDUAL_GAME_FOLDER + crawlFileName;
    File f = new File(fullName);
    if (f.exists()) {
      logger.info(String.format("Already crawled game [%s] in file [%s].", game.getName(), fullName));
      return;
    }

    ApiCounter apiCounter = conf.getApiCounter();
    String endpoint = String.format(game.getGbApiUrl() + REQUEST_API_KEY, apiCounter.getApiKey());
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
      throw new GameCollectException(e);
    }
  }

  private void collectPs4GameInfo(GameCollectConf conf) {
    CollectBasicGamesJob collectPS4Job = new CollectBasicGamesJob(
        CrawlJobId.COLLECT_BASIC_GAME_INFO_JOB_ID,
        "collect ps4 games",
        String.format(API_BASE + GAMES_ENDPOINT + REQUEST_API_KEY + PLATFORM_FILTER,
            conf.getApiCounter().getApiKey(), PS4_PLATFORM_ID),
        "ps4games",
        CRAWLED_PS4_GAME_LIST_FOLDER,
        UPDATED_PS4_GAME_LIST_FOLDER,
        conf, gameService);
    ThreadPool.getThreadPool().execute(collectPS4Job);
  }

  private void collectPs3GameInfo(GameCollectConf conf) {
    CollectBasicGamesJob collectPS3Job = new CollectBasicGamesJob(
        CrawlJobId.COLLECT_BASIC_GAME_INFO_JOB_ID,
        "collect ps3 games",
        String.format(API_BASE + GAMES_ENDPOINT + REQUEST_API_KEY + PLATFORM_FILTER,
            conf.getApiCounter().getApiKey(), PS3_PLATFORM_ID), 
        "ps3games",
        CRAWLED_PS3_GAME_LIST_FOLDER,
        UPDATED_PS3_GAME_LIST_FOLDER,
        conf, gameService);
    ThreadPool.getThreadPool().execute(collectPS3Job);
  }
}
