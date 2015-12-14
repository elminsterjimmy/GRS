package com.elminster.grs.giantbomb.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elminster.common.constants.Constants.StringConstants;
import com.elminster.common.thread.IJobMonitor;
import com.elminster.common.thread.Job;
import com.elminster.common.util.FileUtil;
import com.elminster.common.util.IOUtil;
import com.elminster.grs.giantbomb.config.Config;
import com.elminster.grs.giantbomb.ds.GiantBombGameResponse;
import com.elminster.grs.giantbomb.service.GaintGameService;
import com.elminster.grs.giantbomb.service.GiantGameCollectService;
import com.elminster.retrieve.web.DefaultWebRetriever;
import com.elminster.retrieve.web.data.Method;
import com.elminster.retrieve.web.data.Response;

@Service
public class GiantGameCollectServiceImpl implements GiantGameCollectService {

  private static final Log logger = LogFactory.getLog(GiantGameCollectServiceImpl.class);

  private static final String CRAWLED_FOLDER = "crawled/";
  private static final String PS3_FOLDER = "ps3/";
  private static final String PS4_FOLDER = "ps4/";

  private static final String API_BASE = Config.INSTANCE.getStringProperty("API_BASE");
  private static final String PS3_PLATFORM_ID = Config.INSTANCE.getStringProperty("PS3_PLATFORM_ID");
  private static final String PS4_PLATFORM_ID = Config.INSTANCE.getStringProperty("PS4_PLATFORM_ID");
  private static final String GAMES_ENDPOINT = Config.INSTANCE.getStringProperty("GAMES_ENDPOINT");
  private static final String PLATFORM_FILTER = Config.INSTANCE.getStringProperty("PLATFORM_FILTER");
  private static final String REQUEST_API_KEY = Config.INSTANCE.getStringProperty("REQUEST_API_KEY");
  private static final String API_KEY = Config.INSTANCE.getStringProperty("API_KEY");
  private static final String OFFSET = Config.INSTANCE.getStringProperty("OFFSET");

  @Autowired
  GaintGameService gameService;

  @Override
  public void collectGameInfo() {
    collectPs3GameInfo();
    collectPs4GameInfo();
  }

  private void collectPs4GameInfo() {
    CollectGameJob collectPS4Job = new CollectGameJob(1, "collect ps4 games", String.format(API_BASE + GAMES_ENDPOINT
        + REQUEST_API_KEY + PLATFORM_FILTER, API_KEY, PS4_PLATFORM_ID), "ps4games", CRAWLED_FOLDER + PS4_FOLDER);
    new Thread(collectPS4Job).run();
  }

  private void collectPs3GameInfo() {
    CollectGameJob collectPS3Job = new CollectGameJob(1, "collect ps3 games", String.format(API_BASE + GAMES_ENDPOINT
        + REQUEST_API_KEY + PLATFORM_FILTER, API_KEY, PS3_PLATFORM_ID), "ps3games", CRAWLED_FOLDER + PS3_FOLDER);
    new Thread(collectPS3Job).run();
  }

  class CollectGameJob extends Job {

    String collectUrl;
    String crawledFolder;
    String crawledFilePrefix;

    public CollectGameJob(long id, String name, String collectUrl, String crawledFilePrefix, String crawledFolder) {
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
        DefaultWebRetriever retriever = new DefaultWebRetriever(String.format(collectUrl + OFFSET, offset),
            Method.GET_METHOD);
        Response response = null;
        try {
          response = retriever.retrieve();
          InputStream is = response.getBodyAsInputStream();
          String crawlFileName = crawledFilePrefix + StringConstants.UNDER_LINE + offset + ".xml";
          FileUtil.createFolder(crawledFolder + crawlFileName);
          File f = new File(crawledFolder + crawlFileName);
          try (FileWriter fw = new FileWriter(f)) {
            IOUtil.copy(is, fw);
          } catch (IOException e) {
            throw e;
          }
          JAXBContext jaxbContext = JAXBContext.newInstance(GiantBombGameResponse.class);
          Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
          Object obj = jaxbUnmarshaller.unmarshal(f);
          GiantBombGameResponse o = (GiantBombGameResponse) obj;

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
}
