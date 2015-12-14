package com.elminster.grs.giantbomb.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elminster.common.thread.IJobMonitor;
import com.elminster.common.thread.Job;
import com.elminster.common.util.IOUtil;
import com.elminster.grs.giantbomb.config.Config;
import com.elminster.grs.giantbomb.ds.GiantBombGameResponse;
import com.elminster.grs.giantbomb.ds.SingleGame;
import com.elminster.grs.giantbomb.service.GaintGameService;
import com.elminster.grs.giantbomb.service.GiantGameCollectService;
import com.elminster.retrieve.web.DefaultWebRetriever;
import com.elminster.retrieve.web.data.Method;
import com.elminster.retrieve.web.data.Response;

@Service
public class GiantGameCollectServiceImpl implements GiantGameCollectService {

  private static final String API_BASE = Config.INSTANCE.getStringProperty("API_BASE");
  private static final String PS3_PLATFORM_ID = Config.INSTANCE.getStringProperty("PS3_PLATFORM_ID");
  private static final String GAMES_ENDPOINT = Config.INSTANCE.getStringProperty("GAMES_ENDPOINT");
  private static final String PLATFORM_FILTER = Config.INSTANCE.getStringProperty("PLATFORM_FILTER");
  private static final String REQUEST_API_KEY = Config.INSTANCE.getStringProperty("REQUEST_API_KEY");
  private static final String API_KEY = Config.INSTANCE.getStringProperty("API_KEY");
  private static final String FORMAT_JSON = Config.INSTANCE.getStringProperty("FORMAT_JSON");
  private static final String OFFSET = Config.INSTANCE.getStringProperty("OFFSET");
  
  @Autowired
  GaintGameService gameService;
  
  @Override
  public void collectGameInfo() {
    // TODO Auto-generated method stub
    collectPs3GameInfo();
    
  }

  private void collectPs3GameInfo() {
    Job job = new Job(1, "Collect ps3 games.") {

      @Override
      protected JobStatus doWork(IJobMonitor monitor) {
        monitor.beginJob("Collect ps3 games", 100);
        int offset = 0;
        int total = 0;
        do {
          DefaultWebRetriever retriever = new DefaultWebRetriever(String.format(API_BASE + GAMES_ENDPOINT + REQUEST_API_KEY + PLATFORM_FILTER + OFFSET, API_KEY
              , PS3_PLATFORM_ID, offset), Method.GET_METHOD);
          Response response = null;
          try {
          response = retriever.retrieve();
          InputStream is = response.getBodyAsInputStream();
            File f = new File("ps3games_" + offset + ".xml");
          try (FileWriter fw = new FileWriter(f)) {
            IOUtil.copy(is, fw);
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          JAXBContext jaxbContext = JAXBContext.newInstance(GiantBombGameResponse.class);
          Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
          Object obj = jaxbUnmarshaller.unmarshal(f);
          GiantBombGameResponse o = (GiantBombGameResponse) obj;
          
          int numberOfPageResults = o.getNumber_of_page_results();
          int numberOfTotalResults = o.getNumber_of_total_results();
          total = numberOfTotalResults;
          offset += numberOfPageResults;
          
//          List<SingleGame> result = o.getResults();
//          if (null != result) {
//            for (SingleGame game : result) {
//              gameService.saveGame(game);
//            }
//          }
        } catch (Exception e) {
          e.printStackTrace();
          return JobStatus.ERROR;
        }
        } while (!monitor.isCancelled() && offset < total);
        return monitor.done();
      }
      
    };
    job.run();
  }

}
