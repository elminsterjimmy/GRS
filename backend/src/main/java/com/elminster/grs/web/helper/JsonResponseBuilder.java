package com.elminster.grs.web.helper;

import com.elminster.grs.web.response.JsonResponse;

public class JsonResponseBuilder {

  public final static JsonResponseBuilder INSTANCE = new JsonResponseBuilder();
  
  private JsonResponseBuilder() {}
  
  public JsonResponse buildErrorJsonResponse(Exception e) {
    JsonResponse jsonResponse = new JsonResponse();
    jsonResponse.setStatus(JsonResponse.STATUS_ERROR);
    jsonResponse.addError(ResponseErrorHelper.INSTANCE.generateResponseError(e));
    return jsonResponse;
  }
  
  public JsonResponse buildJsonResponse() {
    return new JsonResponse();
  }
  
  public JsonResponse buildJsonResponse(Object data) {
    JsonResponse jsonResponse = new JsonResponse();
    jsonResponse.setData(data);
    return jsonResponse;
  }
}
