package com.elminster.grs.giantbomb.config;

import java.io.IOException;

import com.elminster.common.config.CommonConfiguration;

public class Config extends CommonConfiguration {
  
  public static Config INSTANCE = new Config();
  
  private Config() {}

  protected void loadResources() {
    try {
      properties.load(this.getClass().getClassLoader().getResourceAsStream("GiantBombApi.properties"));
    } catch (IOException e) {
      throw new RuntimeException("Cannot load giantbomb api properties.");
    }
  }
}
