package com.elminster.grs.web.response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elminster.common.constants.Constants.StringConstants;
import com.elminster.grs.web.service.ServiceException;
import com.elminster.web.commons.response.JsonResponse;
import com.elminster.web.commons.response.JsonResponseBuilder;

/**
 * The Json response template.
 * 
 * @author jgu
 * @version 1.0
 */
public abstract class JsonResponseTemplate {
  
  /** the logger. */
  protected static final Log logger = LogFactory.getLog(JsonResponseTemplate.class);
  /** the Json response builder. */
  protected static JsonResponseBuilder jsonResponseBuilder = JsonResponseBuilder.INSTANCE;

  /**
   * Get Json response.
   * @return the Json response
   */
  public JsonResponse getJsonResponse() throws Exception {
    JsonResponse response = null;
    try {
      response = jsonResponseBuilder.buildJsonResponse(callback());
    } catch (Exception e) {
      if (e instanceof ServiceException) {
        ServiceException se = (ServiceException) e;
        String errorMessage = new StringBuilder().append(se.getExceptionCode()).append(StringConstants.SPACE)
            .append(se.getMessage()).toString();
        logger.error(errorMessage);
        response = jsonResponseBuilder.buildErrorJsonResponse(se);
      } else {
        throw e;
      }
    }
    return response;
  }

  /**
   * The callback method.
   * @return callback result
   */
  abstract protected Object callback() throws Exception ;
}