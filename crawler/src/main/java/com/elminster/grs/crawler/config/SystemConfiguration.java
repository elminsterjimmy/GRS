package com.elminster.grs.crawler.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elminster.common.config.CommonConfiguration;
import com.elminster.common.util.FileUtil;

public class SystemConfiguration extends CommonConfiguration {

  /** the Gamespot crawler properties. */
  private static final String GAMESPOT_CRAWLER_PROPERTIES = "GamespotCrawler.properties";
  
  /** the singleton instance. */
  public static final SystemConfiguration INSTANCE = new SystemConfiguration();
  /** the logger. */
  private static final Log logger = LogFactory.getLog(SystemConfiguration.class);

  private SystemConfiguration() {
    super();
  }
  
  /**
   * Load the resource files.
   */
  protected void loadResources() {
    // load the system properties, if not exist create it.
    if (!FileUtil.isFileExist(GAMESPOT_CRAWLER_PROPERTIES)) {
      throw new RuntimeException(String.format("Properties file [%s] not found. Initialize Gamespot crawler failed!!!",
          new File(GAMESPOT_CRAWLER_PROPERTIES).getAbsoluteFile()));
    }
    InputStream is = null;
    try {
      is = new FileInputStream(GAMESPOT_CRAWLER_PROPERTIES);
      properties.load(is);
    } catch (IOException e) {
      throw new IllegalStateException("Cannot initialize the Gamespot crawler. " + e);
    } finally {
      if (null != is) {
        try {
          is.close();
        } catch (IOException e) {
          if (logger.isDebugEnabled()) {
            logger.debug(e);
          }
        }
      }
    }
  }
}
