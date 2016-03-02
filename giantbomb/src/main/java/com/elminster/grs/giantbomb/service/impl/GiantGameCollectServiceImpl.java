package com.elminster.grs.giantbomb.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elminster.common.constants.Constants.StringConstants;
import com.elminster.common.constants.FileExtensionConstants;
import com.elminster.common.pool.ThreadPool;
import com.elminster.common.thread.IJobMonitor;
import com.elminster.common.thread.Job;
import com.elminster.common.util.FileUtil;
import com.elminster.common.util.IOUtil;
import com.elminster.grs.giantbomb.config.Config;
import com.elminster.grs.giantbomb.config.CrawlJobId;
import com.elminster.grs.giantbomb.ds.GiantBombGame;
import com.elminster.grs.giantbomb.ds.GiantBombGame.GiantBombGameStatus;
import com.elminster.grs.giantbomb.ds.GiantBombGamesResponse;
import com.elminster.grs.giantbomb.service.GaintGameService;
import com.elminster.grs.giantbomb.service.GiantGameCollectService;
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
@Service
public class GiantGameCollectServiceImpl implements GiantGameCollectService {

  /** the logger. */
  private static final Log logger = LogFactory.getLog(GiantGameCollectServiceImpl.class);

  public static final String CRAWLED_FOLDER = "crawled/";
  public static final String PS3_FOLDER = "ps3/";
  public static final String PS4_FOLDER = "ps4/";

  public static final String INDIVIDUAL_GAME_FOLDER = "individual/";

  private static final String API_BASE = Config.INSTANCE.getStringProperty("API_BASE");
  private static final String PS3_PLATFORM_ID = Config.INSTANCE.getStringProperty("PS3_PLATFORM_ID");
  private static final String PS4_PLATFORM_ID = Config.INSTANCE.getStringProperty("PS4_PLATFORM_ID");
  private static final String GAMES_ENDPOINT = Config.INSTANCE.getStringProperty("GAMES_ENDPOINT");
  private static final String PLATFORM_FILTER = Config.INSTANCE.getStringProperty("PLATFORM_FILTER");
  private static final String REQUEST_API_KEY = Config.INSTANCE.getStringProperty("REQUEST_API_KEY");
  private static final String API_KEY = Config.INSTANCE.getStringProperty("API_KEY");
  private static final String OFFSET = Config.INSTANCE.getStringProperty("OFFSET");
  /** the Api counter for games endpoint. */
  private static final ApiCounter GAMES_API_CONTER = new ApiCounter("games", 100);
  /** the Api counter for game endpoint. */
  private static final ApiCounter GAME_API_COUNTER = new ApiCounter("game", 100);

  /**
   * the game service.
   */
  @Autowired
  private GaintGameService gameService;

  @Override
  public void collectBasicGameInfo() {
    collectPs3GameInfo();
    collectPs4GameInfo();
  }

  private void collectPs4GameInfo() {
    CollectBasicGamesJob collectPS4Job = new CollectBasicGamesJob(CrawlJobId.COLLECT_BASIC_GAME_INFO_JOB_ID,
        "collect ps4 games", String.format(API_BASE + GAMES_ENDPOINT + REQUEST_API_KEY + PLATFORM_FILTER, API_KEY,
            PS4_PLATFORM_ID), "ps4games", CRAWLED_FOLDER + PS4_FOLDER);
    ThreadPool.getThreadPool().execute(collectPS4Job);
  }

  private void collectPs3GameInfo() {
    CollectBasicGamesJob collectPS3Job = new CollectBasicGamesJob(CrawlJobId.COLLECT_BASIC_GAME_INFO_JOB_ID,
        "collect ps3 games", String.format(API_BASE + GAMES_ENDPOINT + REQUEST_API_KEY + PLATFORM_FILTER, API_KEY,
            PS3_PLATFORM_ID), "ps3games", CRAWLED_FOLDER + PS3_FOLDER);
    ThreadPool.getThreadPool().execute(collectPS3Job);
  }

  class CollectBasicGamesJob extends Job {

    String collectUrl;
    String crawledFolder;
    String crawledFilePrefix;

    public CollectBasicGamesJob(long id, String name, String collectUrl, String crawledFilePrefix, String crawledFolder) {
      super(id, name);
      this.crawledFilePrefix = crawledFilePrefix;
      this.collectUrl = collectUrl;
      this.crawledFolder = crawledFolder;
    }

    @Override
    protected JobStatus doWork(IJobMonitor monitor) {
      int offset = 0;
      int total = 0;
      monitor.beginJob(getName(), 100);
      File crawled = new File(crawledFolder);
      if (crawled.exists()) {
        File[] listFiles = crawled.listFiles();
        int max = 0;
        for (File f : listFiles) {
          String fileName = FileUtil.getFileNameExcludeExtension(f);
          String[] split = fileName.split(StringConstants.UNDER_LINE);
          offset = Integer.parseInt(split[1]);
          if (offset > max) {
            max = offset;
          }
        }
        total = max;
        offset = max;
        logger.info(String.format("Crawled file detected. Start from offset: [%d].", max));
      }

      do {
        // restrict the API call rate.
        GAMES_API_CONTER.acquire();
        DefaultWebRetriever retriever = new DefaultWebRetriever(String.format(collectUrl + OFFSET, offset),
            Method.GET_METHOD);
        Response response = null;
        try {
          response = retriever.retrieve();
          InputStream is = response.getBodyAsInputStream();
          StringBuilder sb = new StringBuilder();
          String crawlFileName = sb.append(crawledFilePrefix).append(StringConstants.UNDER_LINE).append(offset)
              .append(FileExtensionConstants.XML_EXTENSION).toString();
          String fullName = crawledFolder + crawlFileName;
          FileUtil.createFolder(fullName);
          File f = new File(fullName);
          try (FileWriter fw = new FileWriter(f)) {
            IOUtil.copy(is, fw);
          } catch (IOException e) {
            throw e;
          }
          JAXBContext jaxbContext = JAXBContext.newInstance(GiantBombGamesResponse.class);
          Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
          Object obj = jaxbUnmarshaller.unmarshal(f);
          GiantBombGamesResponse o = (GiantBombGamesResponse) obj;

          int numberOfPageResults = o.getNumber_of_page_results();
          int numberOfTotalResults = o.getNumber_of_total_results();
          total = numberOfTotalResults;
          offset += numberOfPageResults;
        } catch (Exception e) {
          logger.error(e);
          return JobStatus.ERROR;
        }
      } while (!monitor.isCancelled() && offset < total);
      return monitor.done();
    }
  }

  @Override
  public void collectDetailGameInfo() {
    Job job = new CollectDetailGameInfoJob();
    ThreadPool.getThreadPool().execute(job);
  }

  class CollectDetailGameInfoJob extends Job {

    public CollectDetailGameInfoJob() {
      super(CrawlJobId.COLLECT_DETAIL_GAME_INFO_JOB_ID, "Collect detail game information");
    }

    @Override
    protected JobStatus doWork(IJobMonitor monitor) {
      Set<GiantBombGame> games = gameService.findGamesByStatus(GiantBombGameStatus.BASIC_INFO_CRAWLED);
      monitor.beginJob("collect detail game information", games.size());
      for (GiantBombGame game : games) {
        try {
          String endpoint = String.format(game.getGbApiUrl() + REQUEST_API_KEY, API_KEY);
          GAME_API_COUNTER.acquire();
          DefaultWebRetriever retriever = new DefaultWebRetriever(endpoint, Method.GET_METHOD);
          Response response = retriever.retrieve();
          InputStream is = response.getBodyAsInputStream();
          StringBuilder sb = new StringBuilder();
          String crawlFileName = sb.append(game.getInternalId()).append(StringConstants.UNDER_LINE)
              .append(game.getName()).append(StringConstants.UNDER_LINE).append(game.getGamebombId())
              .append(FileExtensionConstants.XML_EXTENSION).toString();
          String fullName = CRAWLED_FOLDER + INDIVIDUAL_GAME_FOLDER + crawlFileName;
          FileUtil.createFolder(fullName);
          File f = new File(fullName);
          try (FileWriter fw = new FileWriter(f)) {
            IOUtil.copy(is, fw);
          } catch (IOException e) {
            throw e;
          }
          game.setStatus(GiantBombGameStatus.DETAIL_INFO_CRAWLED);
          gameService.saveGame(game);
          monitor.worked(1);
        } catch (Exception e) {
          logger.error(e);
        }
      }
      return monitor.done();
    }
  }
}
