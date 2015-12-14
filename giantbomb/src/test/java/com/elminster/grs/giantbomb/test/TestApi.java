package com.elminster.grs.giantbomb.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.elminster.common.retrieve.RetrieveException;
import com.elminster.common.util.IOUtil;
import com.elminster.retrieve.web.DefaultWebRetriever;
import com.elminster.retrieve.web.WebRetriever;
import com.elminster.retrieve.web.data.Method;
import com.elminster.retrieve.web.data.Response;

public class TestApi {

 // @Test
  public void testApi() throws RetrieveException {
    WebRetriever retriever = new DefaultWebRetriever("http://www.giantbomb.com/api/platforms/?api_key=b6c1f62383387c4137eb9706f94ca86a2e8043fd&field_list=id,name&offset=101", Method.GET_METHOD);
    Response response = retriever.retrieve();
    System.out.println(response.getBody());
  }
  
  //@Test
  public void testApi2() throws RetrieveException {
    WebRetriever retriever = new DefaultWebRetriever("http://www.giantbomb.com/api/game/3030-4725/?api_key=b6c1f62383387c4137eb9706f94ca86a2e8043fd", Method.GET_METHOD);
    Response response = retriever.retrieve();
    System.out.println(response.getBody());
  }
  
  @Test
  public void testGamesApi() throws RetrieveException {
    WebRetriever retriever = new DefaultWebRetriever("http://www.giantbomb.com/api/games/?api_key=b6c1f62383387c4137eb9706f94ca86a2e8043fd&platforms=146", Method.GET_METHOD);
    Response response = retriever.retrieve();
    try (FileOutputStream out = new FileOutputStream("responseSample/gamesApi.xml")) {
      InputStream in = response.getBodyAsInputStream();
      IOUtil.copy(in, out);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
