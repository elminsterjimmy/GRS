package com.elminster.grs.web.controller.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.elminster.grs.web.controller.BaseController;
import com.elminster.grs.web.front.model.constants.TemplateName;

@Controller
public class DashboardController extends BaseController {

  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public ModelAndView get(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mv = new ModelAndView(TemplateName.APP_INDEX);
//    ITemplateModel model = new BaseVelocityTemplateModel();
//    ITemplateModelProcessor processor = new VelocityTemplateModelProcessor();
//    processor.processTemplateModel(mv, model);
    return mv;
  }
}
