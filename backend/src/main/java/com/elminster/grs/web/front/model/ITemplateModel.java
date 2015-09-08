package com.elminster.grs.web.front.model;

import java.util.Map;

public interface ITemplateModel {

  public Map<String, Object> getTemplateViewModel();
  
  public Map<String, String> getTemplateMetaModel();
  
  public Map<String, Map<String, Object>> getDomainModel();
}
