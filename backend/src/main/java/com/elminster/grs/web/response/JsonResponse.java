package com.elminster.grs.web.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JsonResponse implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = -6882544319032795924L;
  
  public static final String STATUS_OK = "OK";
  public static final String STATUS_ERROR = "ERROR";
  
  /**
   * The json status.
   */
  private String status;
  /**
   * The json errors.
   */
  private List<ResponseError> errors;
  /**
   * The json data.
   */
  private Object data;
  
  public JsonResponse() {
    this.status = STATUS_OK;
  }
  
  /**
   * @return the status
   */
  public String getStatus() {
    return status;
  }
  /**
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }
  /**
   * @return the errors
   */
  public List<ResponseError> getErrors() {
    return errors;
  }
  /**
   * @param errors the errors to set
   */
  public void setErrors(List<ResponseError> errors) {
    this.errors = errors;
  }
  public void addError(ResponseError error) {
    if (null == this.errors) {
      this.errors = new ArrayList<ResponseError>();
    }
    this.errors.add(error);
  }

  /**
   * @return the data
   */
  public Object getData() {
    return data;
  }

  /**
   * @param data the data to set
   */
  public void setData(Object data) {
    this.data = data;
  }
}