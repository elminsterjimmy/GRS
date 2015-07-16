package com.elminster.grs.web.front.model.constants;

/**
 * The variables used in the Velocity template.
 * 
 * @author jgu
 * @version 1.0
 */
public interface TemplateVariables {
  
  /**
   * The URL variables.
   * 
   * @author jgu
   * @version 1.0
   */
  public interface URLVariables {
    
  }
  
  /**
   * The Meta variables.
   * 
   * @author jgu
   * @version 1.0
   */
  public interface MetaVariables {
    String META_PREFIX = "meta";
    String PAGE_TITLE = "page.title";
    String PAGE_DESCRIPTION = "page.description";
  }
  
  public interface ViewVariables {
    String VIEW_PREFIX = "vm";
    String VIEW_MAIN_CONTENT = "view.main_content";
    String VIEW_MAIN_FOOTER = "view.main_footer";
  }
  
  /**
   * The user relativity variables.
   * 
   * @author jgu
   * @version 1.0
   */
  public interface UserVariables {
    String USER_NAME = "user.name";
    String USER_TITLE = "user.title";
  }
}
