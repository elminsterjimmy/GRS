package com.elminster.grs.crawler.impl;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.springframework.stereotype.Component;

import com.elminster.common.parser.ParseException;
import com.elminster.common.retrieve.RetrieveException;
import com.elminster.grs.crawler.GameInfoCrawler;
import com.elminster.grs.crawler.config.SystemConfiguration;
import com.elminster.grs.crawler.parser.GamespotGameListParser;
import com.elminster.grs.crawler.parser.data.GamespotGameListData;
import com.elminster.retrieve.web.DefaultWebRetriever;
import com.elminster.retrieve.web.WebRetriever;
import com.elminster.retrieve.web.data.Method;
import com.elminster.retrieve.web.data.Response;

@Component("GamespotCrawler")
public class GamespotCrawler implements GameInfoCrawler {
  
  private static final SystemConfiguration configuration = SystemConfiguration.INSTANCE;
  
  private GamespotGameListParser parser = new GamespotGameListParser();

  @Override
  public void crawl() {
    String ps4GameFilter = configuration.getStringProperty("PS4_GAME_FILTER");
    try {
      WebRetriever retriever = new WebRetriever(ps4GameFilter, Method.GET_METHOD) {

        @Override
        protected void configHttpMethod(HttpClient client, HttpMethod httpMethod) throws RetrieveException {
          httpMethod.setRequestHeader("Host", "www.gamespot.com");
          httpMethod.setRequestHeader("Referer", "http://www.gamespot.com/new-games/?sort=date&game_filter_type%5Bplatform%5D=120&game_filter_type%5Bgenres%5D=&game_filter_type%5BminRating%5D=&game_filter_type%5BtimeFrame%5D=&game_filter_type%5BstartDate%5D=&game_filter_type%5BendDate%5D=&game_filter_type%5Btheme%5D=&game_filter_type%5Bregion%5D=&game_filter_type%5Bletter%5D=&page=3");
          httpMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
          httpMethod.setRequestHeader("Upgrade-Insecure-Requests", "1");
          httpMethod.setRequestHeader("Cookie","PHPSESSID=2o6r5cutai55p3hp4lj9j47m50; fly_device=desktop; vguid=92414ac5-4025-4734-9b04-e2ddc5f7ca51; AD_SESSION=o; xcr=as-us; xcab=38-0%7C42-0%7C44-0%7C45-0%7C47-0%7C50-0%7C52-0%7C54-0%7C55-0%7C56-1-1%7C58-0%7C58-0; crit=02adbdc858632e50b78eb6ebea38707a; skinSource=gamespot_white; device_view=full; XCLGFbrowser=LU6tnlXWplZNVxKxbwU; LDCLGFbrowser=d8f6ddb5-6830-4275-851e-44b4cccfd415; hycw4hSBtd=true; JYaH5Y2vxL=true; gebDnVVAmj=10143158706; AMCV_10D31225525FF5790A490D4D%40AdobeOrg=1304406280%7CMCMID%7C42124412313766782602676720154570438511%7CMCAAMLH-1449804904%7C9%7CMCAAMB-1449804904%7CNRX38WO0n5BH8Th-nqAG_A%7CMCAID%7CNONE; __gads=ID=54a91361bf902f78:T=1449200361:S=ALNI_MbdTxjjlLLXKaQjsydx718rK4zdIw; QSI_HistorySession=http%3A%2F%2Fwww.gamespot.com%2F~1449200167266%7Chttp%3A%2F%2Fwww.gamespot.com%2Fps4%2Fgames%2F~1449200261258%7Chttp%3A%2F%2Fwww.gamespot.com%2Fmetal-gear-solid-v-the-phantom-pain%2F~1449200487316%7Chttp%3A%2F%2Fwww.gamespot.com%2Funtil-dawn%2F~1449201857741; ads_firstpg=0; s_vnum=1451792104632%26vn%3D3; _bts=9193779b-ac35-412e-9a89-9841740cd0a8; xDUwNzEzYj=1; sptg=%5B%5D; __CT_Data=gpv=23&apv_6974_www09=23; WRUID=0; s_cc=true; _bti=%7B%22bsin%22%3A%20%22%2BJSStMAPwEGSYtgtEKcK4I3mCa%2BNEn4tROziS9iKDwOe8jtAUX22S1vWAmjPo73Z3a3AG7h4zRX1SWjZ847Q8A%3D%3D%22%2C%22app_id%22%3A%20%220eddb34d4eb4be1df2b4160ec047aa73%22%2C%22app_member_id%22%3A%20null%7D; __utma=14953632.1352993265.1449200102.1449212718.1449223072.3; __utmb=14953632.7.10.1449223073; __utmc=14953632; __utmz=14953632.1449200102.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmv=14953632.|2=UserType=Anonymous=1; aam_uuid=41889304380913561722698241379618222615; sq4YFvJMK2=1; s_getNewRepeat=1449226609692-Repeat; s_lv_gamespot=1449226609693; s_lv_gamespot_s=Less%20than%201%20day; s_invisit=true; s_sq=cbsigamespotsite%252Ccbsicbsiall%3D%2526pid%253Dgs%25253A%25252Fnew-games%25252F%2526pidt%253D1%2526oid%253DRefresh%252520List%2526oidt%253D3%2526ot%253DSUBMIT");
          
          
          /**
           * 
           * 
Accept-Encoding:gzip, deflate, sdch
Accept-Language:en,zh-CN;q=0.8,zh;q=0.6,ja;q=0.4,zh-TW;q=0.2
AlexaToolbar-ALX_NS_PH:AlexaToolbar/alxg-3.3
Connection:keep-alive
           */
          
          httpMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
          httpMethod.setRequestHeader("Accept-Language", "en,zh-CN;q=0.8,zh;q=0.6,ja;q=0.4,zh-TW;q=0.2");
//          httpMethod.setRequestHeader("Accept-Encoding", "gzip, deflate, sdch");
          httpMethod.setRequestHeader("Connection", "keep-alive");
        }

        @Override
        protected void configHttpClient(HttpClient client) throws RetrieveException {
        }
        
      };
      Response response = retriever.retrieve();
      
      parser.setBaseUrl(ps4GameFilter);
      GamespotGameListData data = parser.parse(response.getBody());
      System.out.println(data.toString());
    } catch (RetrieveException | ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    // TODO Auto-generated method stub
  }

}
