package com.elminster.grs.giantbomb.test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.elminster.grs.giantbomb.Application;
import com.elminster.grs.giantbomb.ds.GiantBombGame;
import com.elminster.grs.giantbomb.ds.GiantBombGameResponse;
import com.elminster.grs.giantbomb.service.GaintGameService;
import com.elminster.grs.giantbomb.service.UpdateGameService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestUpdateGameService {

  @Autowired
  UpdateGameService service;
  /**
   * the game service.
   */
  @Autowired
  private GaintGameService gameService;

  //@Test
  public void testUpdateGameService() {
    service.updateGame();
  }
  
  @Test
  public void testUpdateGameDetail() throws Exception {
    File file = new File("D:\\github\\GRS\\giantbomb\\responseSample\\OneGame.xml");
    JAXBContext jaxbContext = JAXBContext.newInstance(GiantBombGameResponse.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    Object obj = jaxbUnmarshaller.unmarshal(file);
    GiantBombGameResponse o = (GiantBombGameResponse) obj;
    
    GiantBombGame game = o.getResults();
    
    gameService.saveGame(game);
    
    
    System.out.println(game);
  }
}
