package com.elminster.grs.web.helper;

import javax.servlet.http.HttpServletRequest;

import com.elminster.common.util.StringUtil;

final public class IpFinder {

  // @formatter:off
  private static final String[] HEADERS_TO_TRY = { 
    "X-Forwarded-For", 
    "Proxy-Client-IP", 
    "WL-Proxy-Client-IP",
    "HTTP_X_FORWARDED_FOR",
    "HTTP_X_FORWARDED",
    "HTTP_X_CLUSTER_CLIENT_IP",
    "HTTP_CLIENT_IP",
    "HTTP_FORWARDED_FOR",
    "HTTP_FORWARDED",
    "HTTP_VIA",
    "REMOTE_ADDR"
  };
  //@formatter:on

  private static final String UNKNOWN = "unknown";

  public static String getRequestIpAddress(HttpServletRequest request) {
    for (String header : HEADERS_TO_TRY) {
      String ip = request.getHeader(header);
      if (StringUtil.isNotEmpty(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
        return ip;
      }
    }
    return request.getRemoteAddr();
  }
}
