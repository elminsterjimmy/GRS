package com.elminster.grs.giantbomb.service.job;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elminster.common.thread.IJobMonitor;
import com.elminster.common.thread.Job;
import com.elminster.common.util.ExceptionUtil;
import com.elminster.common.util.FileUtil;
import com.elminster.grs.giantbomb.ds.GiantBombGame;
import com.elminster.grs.giantbomb.ds.GiantBombGamesResponse;
import com.elminster.grs.giantbomb.service.GiantGameService;

public class UpdateBasicGameJob extends Job {

  private static final Log logger = LogFactory.getLog(UpdateBasicGameJob.class);

  private GiantGameService gameService;
  private GiantBombGamesResponse response;
  private File crawledFile;
  private String updatedDirectory;

  public UpdateBasicGameJob(
      long id, String name,
      File crawledFile,
      String updatedDirectory,
      GiantBombGamesResponse response,
      GiantGameService gameService) {
    super(id, name);
    this.crawledFile = crawledFile;
    this.updatedDirectory = updatedDirectory;
    this.response = response;
    this.gameService = gameService;
  }

  @Override
  protected JobStatus doWork(IJobMonitor monitor) {
    List<GiantBombGame> gameList = response.getResults();
    if (!gameList.isEmpty()) {
      monitor.beginJob(String.format("Updating last crawled file [%s].", crawledFile.getAbsoluteFile()), response.getNumber_of_page_results());
    }
    List<FailedMeta> unsavedGames = new ArrayList<>();
    for (GiantBombGame game : gameList) {
      try {
        gameService.saveGame(game);
      } catch (Exception e) {
        FailedMeta meta = new FailedMeta();
        meta.id = game.getInternalId();
        meta.name = game.getName();
        meta.exception = ExceptionUtil.getFullStackTrace(e);
        unsavedGames.add(meta);
        logger.error(String.format("Exception occured while saving game [{%d}-{%s}]. Caused by: [%s].", game.getInternalId(), game.getName(), ExceptionUtil.getFullStackTrace(e)));
      }
      monitor.worked(1);
    }
    try {
      File updatedDir = new File(updatedDirectory);
      if (!updatedDir.exists()) {
        FileUtil.createFolder(updatedDirectory);
      }
      if (unsavedGames.isEmpty()) {
        FileUtil.copyFile(crawledFile.getAbsolutePath(), FileUtil.fixFolderName(updatedDir.getAbsolutePath()) + crawledFile.getName());
      } else {
        File parent = updatedDir.getParentFile();
        String parentAbsolutePath = parent.getAbsolutePath();
        String saveFailedDir = parentAbsolutePath + "/saveFailed/";
        FileUtil.copyFile(crawledFile.getAbsolutePath(), saveFailedDir + crawledFile.getName());
        List<String> contents = new ArrayList<String>();
        for (FailedMeta meta : unsavedGames) {
          contents.add(String.format("{%d}-{%s}: %s", meta.id, meta.name, meta.exception));
        }
        String metaFileName = FileUtil.changeFileExtension(crawledFile.getName(), "meta");
        FileUtil.writeLines2file(contents, saveFailedDir + metaFileName, false);
      }
    } catch (Exception e) {
      logger.error(String.format("Exception occured while updating the crawled file [%s]. Caused by: [%s].", crawledFile.getAbsoluteFile(), ExceptionUtil.getFullStackTrace(e)));
    }
    return monitor.done();
  }

  class FailedMeta {
    String name;
    int id;
    String exception;
  }
}
