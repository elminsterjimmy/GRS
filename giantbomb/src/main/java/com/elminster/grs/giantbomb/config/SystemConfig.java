package com.elminster.grs.giantbomb.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elminster.common.config.CommonConfiguration;

public class SystemConfig extends CommonConfiguration {
  
  private static final String SYS_CONFIG_FILE = "GiantBombSystem.properties";
  private static final Log logger = LogFactory.getLog(SystemConfig.class);
  
  public static final BooleanKey KEY_AUTO_START_CRAWLER = new BooleanKey("giantbomb.crawler.auto", true);

  public static SystemConfig INSTANCE = new SystemConfig();
  
  private SystemConfig() {}

  protected void loadResources() {
    File file = new File(SYS_CONFIG_FILE);
    try (InputStream fis = new FileInputStream(file)) {
      properties.load(fis);
    } catch (IOException e) {
      logger.warn(String.format("Giant Bomb System Configuration [%s] is not found! Use DEFAULT Configuration.", SYS_CONFIG_FILE));
    }
  }
}
