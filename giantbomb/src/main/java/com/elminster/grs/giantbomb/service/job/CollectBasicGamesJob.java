package com.elminster.grs.giantbomb.service.job;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elminster.common.constants.Constants.StringConstants;
import com.elminster.common.pool.ThreadPool;
import com.elminster.common.constants.FileExtensionConstants;
import com.elminster.common.thread.IJobMonitor;
import com.elminster.common.thread.Job;
import com.elminster.common.util.DateUtil;
import com.elminster.common.util.ExceptionUtil;
import com.elminster.common.util.FileUtil;
import com.elminster.common.util.IOUtil;
import com.elminster.grs.giantbomb.config.Config;
import com.elminster.grs.giantbomb.ds.GiantBombGamesResponse;
import com.elminster.grs.giantbomb.service.GaintGameService;
import com.elminster.grs.giantbomb.service.GameCollectConf;
import com.elminster.grs.giantbomb.util.ApiCounter;
import com.elminster.retrieve.web.DefaultWebRetriever;
import com.elminster.retrieve.web.data.Method;
import com.elminster.retrieve.web.data.Response;

public class CollectBasicGamesJob extends Job {

  private static final Log logger = LogFactory.getLog(CollectBasicGamesJob.class);

  private static final String OFFSET = Config.INSTANCE.getStringProperty("OFFSET");

  private String collectUrl;
  private String crawledDirectory;
  private String updatedDirectory;
  private String crawledFilePrefix;
  private GameCollectConf conf;
  private GaintGameService gameService;

  public CollectBasicGamesJob(
      long id,
      String name,
      String collectUrl,
      String crawledFilePrefix,
      String crawledDirectory,
      String updatedDirectory,
      GameCollectConf conf,
      GaintGameService gameService) {
    super(id, name);
    this.crawledFilePrefix = crawledFilePrefix;
    this.collectUrl = collectUrl;
    this.crawledDirectory = crawledDirectory;
    this.updatedDirectory = updatedDirectory;
    this.conf = conf;
    this.gameService = gameService;
  }

  @Override
  protected JobStatus doWork(IJobMonitor monitor) {
    int offset = 0;
    int total = 0;
    int from = conf.getFrom();
    int to = conf.getTo();
    ApiCounter apiCounter = conf.getApiCounter();
    monitor.beginJob(getName(), 100);
    File crawled = new File(crawledDirectory);
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
      offset = Math.max(max, from);
      logger.info(String.format("Crawled file detected. Start from offset: [%d].", max));
    }

    do {
      // restrict the API call rate.
      apiCounter.acquire();
      DefaultWebRetriever retriever = new DefaultWebRetriever(String.format(collectUrl + OFFSET, offset), Method.GET_METHOD);
      Response response = null;
      try {
        response = retriever.retrieve();
        InputStream is = response.getBodyAsInputStream();
        StringBuilder sb = new StringBuilder();
        String crawlFileName = sb.append(crawledFilePrefix).append(StringConstants.UNDER_LINE).append(offset).append(FileExtensionConstants.XML_EXTENSION).toString();
        String fullName = crawledDirectory + crawlFileName;
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

        UpdateBasicGameJob updateJob = new UpdateBasicGameJob(
            this.getId() + offset,
            String.format("Update Basic Game Information [%s]", f.getAbsolutePath()),
            f, updatedDirectory, o, gameService);
        ThreadPool.getThreadPool().execute(updateJob);
        
      } catch (Exception e) {
        logger.error(String.format("Failed to crawl game list. Cause [%s].", ExceptionUtil.getFullStackTrace(e)));
      }
      if (conf.isEndless()) {
        if (offset >= total) {
          // end of list
          // wait 1 hour to check if new data is available
          try {
            logger.debug("No New Basic Information available, wait 1 hours to retry.");
            Thread.sleep(DateUtil.HOUR);
          } catch (InterruptedException e) {
            logger.error("Basic Game Collect Job is Interrupted.");
            return monitor.cancel();
          }
        }
      } else {
        if (offset >= to) {
          return monitor.done();
        }
      }
    } while (!monitor.isCancelled() && !Thread.interrupted());
    return monitor.done();
  }
}