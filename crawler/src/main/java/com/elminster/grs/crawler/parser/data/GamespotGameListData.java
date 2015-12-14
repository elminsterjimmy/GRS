package com.elminster.grs.crawler.parser.data;

import java.util.List;

public class GamespotGameListData {

  private String baseUrl;
  
  private List<String> gameUrls;
  
  private String nextUrl;

  /**
   * @return the baseUrl
   */
  public String getBaseUrl() {
    return baseUrl;
  }

  /**
   * @param baseUrl the baseUrl to set
   */
  public void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  /**
   * @return the gameUrls
   */
  public List<String> getGameUrls() {
    return gameUrls;
  }

  /**
   * @param gameUrls the gameUrls to set
   */
  public void setGameUrls(List<String> gameUrls) {
    this.gameUrls = gameUrls;
  }

  /**
   * @return the nextUrl
   */
  public String getNextUrl() {
    return nextUrl;
  }

  /**
   * @param nextUrl the nextUrl to set
   */
  public void setNextUrl(String nextUrl) {
    this.nextUrl = nextUrl;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "GamespotGameListData [baseUrl=" + baseUrl + ", gameUrls=" + gameUrls + ", nextUrl=" + nextUrl + "]";
  }
}
