package com.elminster.grs.giantbomb.ds;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class GiantBombGameResponse {
  String error;
  int limit;
  int offset;
  int number_of_page_results;
  int number_of_total_results;
  int status_code;
  List<SingleGame> results;
  /**
   * @return the error
   */
  public String getError() {
    return error;
  }
  /**
   * @param error the error to set
   */
  public void setError(String error) {
    this.error = error;
  }
  /**
   * @return the limit
   */
  public int getLimit() {
    return limit;
  }
  /**
   * @param limit the limit to set
   */
  public void setLimit(int limit) {
    this.limit = limit;
  }
  /**
   * @return the offset
   */
  public int getOffset() {
    return offset;
  }
  /**
   * @param offset the offset to set
   */
  public void setOffset(int offset) {
    this.offset = offset;
  }
  /**
   * @return the number_of_page_results
   */
  public int getNumber_of_page_results() {
    return number_of_page_results;
  }
  /**
   * @param number_of_page_results the number_of_page_results to set
   */
  public void setNumber_of_page_results(int number_of_page_results) {
    this.number_of_page_results = number_of_page_results;
  }
  /**
   * @return the number_of_total_results
   */
  public int getNumber_of_total_results() {
    return number_of_total_results;
  }
  /**
   * @param number_of_total_results the number_of_total_results to set
   */
  public void setNumber_of_total_results(int number_of_total_results) {
    this.number_of_total_results = number_of_total_results;
  }
  /**
   * @return the status_code
   */
  public int getStatus_code() {
    return status_code;
  }
  /**
   * @param status_code the status_code to set
   */
  public void setStatus_code(int status_code) {
    this.status_code = status_code;
  }
  /**
   * @return the results
   */
  public List<SingleGame> getResults() {
    return results;
  }
  /**
   * @param results the results to set
   */
  @XmlElementWrapper(name="results")
  @XmlElement(name="game")
  public void setResults(List<SingleGame> results) {
    this.results = results;
  }
}
