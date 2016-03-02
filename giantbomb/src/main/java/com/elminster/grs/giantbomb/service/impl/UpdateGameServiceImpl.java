package com.elminster.grs.giantbomb.service.impl;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.elminster.common.constants.Constants.StringConstants;
import com.elminster.common.util.DateUtil;
import com.elminster.common.util.ExceptionUtil;
import com.elminster.common.util.FileUtil;
import com.elminster.grs.giantbomb.ds.GiantBombGame;
import com.elminster.grs.giantbomb.ds.GiantBombGamesResponse;
import com.elminster.grs.giantbomb.service.GaintGameService;
import com.elminster.grs.giantbomb.service.UpdateGameService;

/**
 * The service to update the crawled game information.
 * 
 * @author jgu
 * @version 1.0
 */
@Component
public class UpdateGameServiceImpl implements UpdateGameService {

  /** the logger. */
  private static final Log logger = LogFactory.getLog(UpdateGameServiceImpl.class);
  /** the update rate: 30 minutes. */
  private static final long SCHEDULE_FIXED_RATE = 30 * DateUtil.MINUTE;
  /** the updated folder. */
  public static final String UPDATED_FOLDER = "updated/";

  /**
   * the game service.
   */
  @Autowired
  private GaintGameService gameService;

  /**
   * Update games.
   */
  @Scheduled(fixedRate = SCHEDULE_FIXED_RATE)
  @Override
  public void updateGame() {
    // TODO 
    File crawledPs4Folder = new File(GiantGameCollectServiceImpl.CRAWLED_FOLDER + GiantGameCollectServiceImpl.PS4_FOLDER);
    updateBasicGameInfo(crawledPs4Folder, GiantGameCollectServiceImpl.PS4_FOLDER);
  }

  /**
   * Update basic game information.
   * @param crawledFolder the crawled folder
   * @param updateFolder the update folder
   */
  private void updateBasicGameInfo(File crawledFolder, String updateFolder) {
    File[] crawledFiles = crawledFolder.listFiles();
    File updatedFolder = new File(UPDATED_FOLDER + updateFolder);
    if (!updatedFolder.exists()) {
      updatedFolder.mkdirs();
    }
    File lastCrawledFile = null;
    for (File crawledFile : crawledFiles) {
      try {
        String filename = crawledFile.getName();
        int max = 0;
        int offset = 0;
        String fileName = FileUtil.getFileNameExcludeExtension(crawledFile);
        String[] split = fileName.split(StringConstants.UNDER_LINE);
        offset = Integer.parseInt(split[1]);
        if (offset > max) {
          max = offset;
          lastCrawledFile = crawledFile;
        }
        if (FileUtil.contains(updatedFolder, filename, true, false)) {
          logger.info(String.format("Crawled file [%d] already been updated.", crawledFile.getAbsolutePath()));
        } else {
          updateWithCrawledFile(crawledFile, updatedFolder);
        }
      } catch (Exception e) {
        logger.error(String.format("Exception occured while updating the crawled file [%s]. Caused by: [%s].", crawledFile.getAbsoluteFile(), ExceptionUtil.getFullStackTrace(e)));
      }
    }
    try {
      logger.info(String.format("Updating last crawled file [%s].", lastCrawledFile.getAbsoluteFile()));
      updateWithCrawledFile(lastCrawledFile, updatedFolder);
    } catch (Exception e) {
      logger.error(String.format("Exception occured while updating the crawled file [%s]. Caused by: [%s].", lastCrawledFile.getAbsoluteFile(), ExceptionUtil.getFullStackTrace(e)));
    }
  }

  /**
   * Update the game information with the crawled file.
   * @param crawledFile the crawled file
   * @param updatedFolder the updated folder
   * @throws Exception on error
   */
  private void updateWithCrawledFile(File crawledFile, File updatedFolder) throws Exception {
    JAXBContext jaxbContext = JAXBContext.newInstance(GiantBombGamesResponse.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    Object obj = jaxbUnmarshaller.unmarshal(crawledFile);
    GiantBombGamesResponse o = (GiantBombGamesResponse) obj;
    List<GiantBombGame> gameList = o.getResults();
    for (GiantBombGame game : gameList) {
      gameService.saveGame(game);
    }
    FileUtil.copyFile(crawledFile.getAbsolutePath(), FileUtil.fixFolderName(updatedFolder.getAbsolutePath())
        + crawledFile.getName());
  }
}