package com.elminster.grs.web.front.model;

import org.springframework.web.servlet.ModelAndView;

public interface ITemplateModelProcessor {

  public void processTemplateModel(ModelAndView modelAndView, ITemplateModel templateModel);
}
