package com.elminster.grs.crawler.test;

import org.junit.Test;

import com.elminster.grs.crawler.impl.GamespotCrawler;

public class GamespotCrawlerTest {

  @Test
  public void testGamespotCrawler() {
    GamespotCrawler crawler = new GamespotCrawler();
    crawler.crawl();
  }
}
