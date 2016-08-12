package com.elminster.grs.giantbomb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elminster.common.pool.ThreadPool;
import com.elminster.common.thread.Job;
import com.elminster.common.util.DateUtil;
import com.elminster.grs.giantbomb.config.Config;
import com.elminster.grs.giantbomb.config.CrawlJobId;
import com.elminster.grs.giantbomb.service.CollectConf;
import com.elminster.grs.giantbomb.service.GiantBombAutoCollectService;
import com.elminster.grs.giantbomb.service.GiantBombCollectService;
import com.elminster.grs.giantbomb.service.GiantCompanyService;
import com.elminster.grs.giantbomb.service.GiantGameService;
import com.elminster.grs.giantbomb.service.GiantGenreService;
import com.elminster.grs.giantbomb.service.GiantPlatformService;
import com.elminster.grs.giantbomb.service.GiantThemeService;
import com.elminster.grs.giantbomb.service.GiantVideoService;
import com.elminster.grs.giantbomb.service.InternalFS;
import com.elminster.grs.giantbomb.service.job.CollectBasicGamesJob;
import com.elminster.grs.giantbomb.service.job.CollectDetailCompanyInfoJob;
import com.elminster.grs.giantbomb.service.job.CollectDetailGameInfoJob;
import com.elminster.grs.giantbomb.service.job.CollectDetailGenreInfoJob;
import com.elminster.grs.giantbomb.service.job.CollectDetailPlatformInfoJob;
import com.elminster.grs.giantbomb.service.job.CollectDetailThemeInfoJob;
import com.elminster.grs.giantbomb.service.job.CollectDetailVideoInfoJob;
import com.elminster.grs.giantbomb.util.ApiCounter;
import com.elminster.grs.giantbomb.util.ApiCounterManager;

@Service
public class GiantBombAutoCollectServiceImpl implements GiantBombAutoCollectService, InternalFS {
  
  private static final String API_BASE = Config.INSTANCE.getStringProperty("API_BASE");
  private static final String API_KEY = Config.INSTANCE.getStringProperty("API_KEY");

  private static final String PS3_PLATFORM_ID = Config.INSTANCE.getStringProperty("PS3_PLATFORM_ID");
  private static final String PS4_PLATFORM_ID = Config.INSTANCE.getStringProperty("PS4_PLATFORM_ID");
  private static final String GAMES_ENDPOINT = Config.INSTANCE.getStringProperty("GAMES_ENDPOINT");
  private static final String PLATFORM_FILTER = Config.INSTANCE.getStringProperty("PLATFORM_FILTER");
  private static final String REQUEST_API_KEY = Config.INSTANCE.getStringProperty("REQUEST_API_KEY");
  
  private static final ApiCounter INTERNAL_GAMES_API_CONTER = 
      ApiCounterManager.INSTANCE.getApiCounter("games", API_KEY, 180, DateUtil.HOUR);
  private static final ApiCounter INTERNAL_GAME_API_COUNTER = 
      ApiCounterManager.INSTANCE.getApiCounter("game", API_KEY, 180, DateUtil.HOUR);
  private static final ApiCounter INTERNAL_PLATFORM_API_COUNTER = 
      ApiCounterManager.INSTANCE.getApiCounter("platform", API_KEY, 180, DateUtil.HOUR);
  private static final ApiCounter INTERNAL_COMPANY_API_COUNTER = 
      ApiCounterManager.INSTANCE.getApiCounter("company", API_KEY, 180, DateUtil.HOUR);
  private static final ApiCounter INTERNAL_GENRE_API_COUNTER = 
      ApiCounterManager.INSTANCE.getApiCounter("genre", API_KEY, 180, DateUtil.HOUR);
  private static final ApiCounter INTERNAL_THEME_API_COUNTER = 
      ApiCounterManager.INSTANCE.getApiCounter("theme", API_KEY, 180, DateUtil.HOUR);
  private static final ApiCounter INTERNAL_VIDEO_API_COUNTER = 
      ApiCounterManager.INSTANCE.getApiCounter("vedio", API_KEY, 180, DateUtil.HOUR);
  
  
  @Autowired
  private GiantGameService gameService;
  @Autowired
  private GiantPlatformService platformService;
  @Autowired
  private GiantCompanyService companyService;
  @Autowired
  private GiantGenreService genreService;
  @Autowired
  private GiantThemeService themeService;
  @Autowired
  private GiantVideoService videoService;
  @Autowired
  private GiantBombCollectService collectService;
  
  @Override
  public void autoRun() {
    collectBasicGameInfo();
    
    collectDetailGameInfo();
    collectDetailPlatformInfo();
    collectDetailCompanyInfo();
    collectDetailGenreInfo();
  }

  public void collectBasicGameInfo() {
    CollectConf conf = new CollectConf();
    conf.setApiCounter(INTERNAL_GAMES_API_CONTER);
    
    collectPs3GameInfo(conf);
    collectPs4GameInfo(conf);
  }

  public void collectDetailGameInfo() {
    CollectConf conf = new CollectConf();
    conf.setApiCounter(INTERNAL_GAME_API_COUNTER);
    Job job = new CollectDetailGameInfoJob(
        CrawlJobId.COLLECT_DETAIL_GAME_INFO_JOB_ID,
        "Collect detail game information",
        conf,
        collectService,
        gameService);
    ThreadPool.getThreadPool().execute(job);
  }
  
  public void collectDetailPlatformInfo() {
    CollectConf conf = new CollectConf();
    conf.setApiCounter(INTERNAL_PLATFORM_API_COUNTER);
    Job job = new CollectDetailPlatformInfoJob(
        CrawlJobId.COLLECT_DETAIL_PLATFORM_INFO_JOB_ID,
        "Collect detail platform information",
        conf,
        collectService,
        platformService);
    ThreadPool.getThreadPool().execute(job);
  }
  
  public void collectDetailCompanyInfo() {
    CollectConf conf = new CollectConf();
    conf.setApiCounter(INTERNAL_COMPANY_API_COUNTER);
    Job job = new CollectDetailCompanyInfoJob(
        CrawlJobId.COLLECT_DETAIL_COMPANY_INFO_JOB_ID,
        "Collect detail company information",
        conf,
        collectService,
        companyService);
    ThreadPool.getThreadPool().execute(job);
  }
  
  public void collectDetailGenreInfo() {
    CollectConf conf = new CollectConf();
    conf.setApiCounter(INTERNAL_GENRE_API_COUNTER);
    Job job = new CollectDetailGenreInfoJob(
        CrawlJobId.COLLECT_DETAIL_GENRE_INFO_JOB_ID,
        "Collect detail genre information",
        conf,
        collectService,
        genreService);
    ThreadPool.getThreadPool().execute(job);
  }

  public void collectDetailThemeInfo() {
    CollectConf conf = new CollectConf();
    conf.setApiCounter(INTERNAL_THEME_API_COUNTER);
    Job job = new CollectDetailThemeInfoJob(
        CrawlJobId.COLLECT_DETAIL_THEME_INFO_JOB_ID,
        "Collect detail theme information",
        conf,
        collectService,
        themeService);
    ThreadPool.getThreadPool().execute(job);
  }
  
  public void collectDetailVideoInfo() {
    CollectConf conf = new CollectConf();
    conf.setApiCounter(INTERNAL_VIDEO_API_COUNTER);
    Job job = new CollectDetailVideoInfoJob(
        CrawlJobId.COLLECT_DETAIL_VIDEO_INFO_JOB_ID,
        "Collect detail video information",
        conf,
        collectService,
        videoService);
    ThreadPool.getThreadPool().execute(job);
  }

  private void collectPs4GameInfo(CollectConf conf) {
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

  private void collectPs3GameInfo(CollectConf conf) {
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
