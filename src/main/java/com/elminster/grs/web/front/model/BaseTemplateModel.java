package com.elminster.grs.web.front.model;

import java.util.HashMap;
import java.util.Map;

import com.elminster.grs.web.front.model.constants.TemplateVariables.MetaVariables;
import com.elminster.grs.web.front.model.constants.TemplateVariables.ViewVariables;

public class BaseTemplateModel implements ITemplateModel {

  
  protected Map<String, String> metaMap = new HashMap<String, String>();
  
  protected Map<String, Object> viewMap = new HashMap<String, Object>();
  
  protected Map<String, Map<String, Object>> domainMap = new HashMap<String, Map<String, Object>>();

  @Override
  public Map<String, Object> getTemplateViewModel() {
    return viewMap;
  }

  @Override
  public Map<String, String> getTemplateMetaModel() {
    return metaMap;
  }
  
  @Override
  public Map<String, Map<String, Object>> getDomainModel() {
    return domainMap;
  }
  
  public void setMainContentTemplate(String mainContentTemplate) {
    assert null != mainContentTemplate;
    viewMap.put(ViewVariables.VIEW_MAIN_CONTENT, mainContentTemplate);
  }
  
  public void setMainFooterTemplate(String mainFooterTemplate) {
    viewMap.put(ViewVariables.VIEW_MAIN_FOOTER, mainFooterTemplate);
  }
  
  public void setPageTitle(String title) {
    metaMap.put(MetaVariables.PAGE_TITLE, title);
  }
  
  public void setPageDescription(String description) {
    metaMap.put(MetaVariables.PAGE_DESCRIPTION, description);
  }
}
