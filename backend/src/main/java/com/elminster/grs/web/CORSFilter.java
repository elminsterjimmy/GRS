package com.elminster.grs.web;

import java.io.File;

import org.springframework.stereotype.Component;

import com.elminster.common.config.DynamicConfiguration;
import com.elminster.web.commons.filter.SimpleCORSFilter;

@Component
public class CORSFilter extends SimpleCORSFilter {

  private static final String PROPERTIES_FILE_PATH = "CORS.properties";
  private static final String ALLOWED_ORIGIN_KEY = "CORS.Allow.Origin";
  private static DynamicConfiguration configuration = new DynamicConfiguration(PROPERTIES_FILE_PATH);
  
  File f = new File(PROPERTIES_FILE_PATH);
  String configedAllowOrigin = configuration.getStringProperty(ALLOWED_ORIGIN_KEY);
}
