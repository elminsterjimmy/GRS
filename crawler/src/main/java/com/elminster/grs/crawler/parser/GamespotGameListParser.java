package com.elminster.grs.crawler.parser;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.elminster.common.parser.ParseException;
import com.elminster.common.util.XMLUtil;
import com.elminster.grs.crawler.config.SystemConfiguration;
import com.elminster.grs.crawler.parser.data.GamespotGameListData;
import com.elminster.retrieve.parser.web.HttpContentParser;

/**
 * This parser parses gamespot's game list for the game filter into the individual game information URL list.
 * 
 * @author jgu
 * @version 1.0
 */
public class GamespotGameListParser extends HttpContentParser<GamespotGameListData> {

  private static final SystemConfiguration configuration = SystemConfiguration.INSTANCE;

  private String baseUrl;

  public void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected GamespotGameListData parseDoc(Document doc) throws ParseException {
    try {
      NodeList list = XMLUtil.xpathEvaluateNodeList(configuration.getStringProperty("XPATH_TO_INDIVIDUAL_GAME_URL"),
          doc);
      GamespotGameListData data = new GamespotGameListData();
      data.setBaseUrl(baseUrl);
      if (null != list) {
        List<String> gameUrls = new ArrayList<String>();
        for (int i = 0; i < list.getLength(); i++) {
          Node node = list.item(i);
          gameUrls.add(node.getTextContent());
        }
        data.setGameUrls(gameUrls);
      }
      String nextPage = XMLUtil
          .xpathEvaluateString(configuration.getStringProperty("XPATH_TO_NEXT_GAME_LIST_URL"), doc);
      if (null != nextPage) {
        data.setNextUrl(nextPage);
      }
      return data;
    } catch (Exception e) {
      throw new ParseException(String.format("Parse game list failed for URL [%s].", baseUrl), e);
    }
  }
}
