package com.elminster.grs.web.front.model.velocity;

import java.util.Map;
import java.util.Set;

import org.springframework.web.servlet.ModelAndView;

import com.elminster.common.util.CollectionUtil;
import com.elminster.grs.web.front.model.ITemplateModel;
import com.elminster.grs.web.front.model.ITemplateModelProcessor;
import com.elminster.grs.web.front.model.constants.TemplateVariables.MetaVariables;
import com.elminster.grs.web.front.model.constants.TemplateVariables.ViewVariables;

public class VelocityTemplateModelProcessor implements ITemplateModelProcessor, MetaVariables, ViewVariables {

  @Override
  public void processTemplateModel(ModelAndView modelAndView, ITemplateModel templateModel) {
    if (null == modelAndView || null == templateModel) {
      return;
    }
    modelAndView.addObject(META_PREFIX, templateModel.getTemplateMetaModel());
    modelAndView.addObject(VIEW_PREFIX, templateModel.getTemplateMetaModel());
    Map<String, Map<String, Object>> domainModels = templateModel.getDomainModel();
    if (CollectionUtil.isNotEmpty(domainModels)) {
      Set<String> keySet = domainModels.keySet();
      for (String key : keySet) {
        modelAndView.addObject(key, domainModels.get(key));
      }
    }
  }

}
