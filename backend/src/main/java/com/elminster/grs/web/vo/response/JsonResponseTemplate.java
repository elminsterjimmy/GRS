package com.elminster.grs.web.vo.response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elminster.common.constants.Constants.StringConstants;
import com.elminster.grs.web.service.ServiceException;
import com.elminster.web.commons.request.Option;
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
        String errorMessage = new StringBuilder().append(se.getErrorCode()).append(StringConstants.SPACE)
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
   * Get Json response with option.
   * @param option option
   * @return the Json response
   */
  public JsonResponse getJsonResponse(Option option) throws Exception {
    JsonResponse response = getJsonResponse();
    // TODO add extra information about the response, such as next page for paging.
    return response;
  }

  /**
   * The callback method.
   * @return callback result
   */
  abstract protected Object callback() throws Exception ;
}