package com.elminster.grs.web.security;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.elminster.common.util.EncryptUtil;
import com.elminster.grs.web.security.model.TokenUserDetails;
import com.elminster.grs.web.utils.JSONUtils;

@Component
public class TokenHelper {
  
  private static final String SECURITY_SEED = "com.elminster.grs.security";
  
  public String generateToken4User(TokenUserDetails user) {
    try {
      String jsonString = JSONUtils.INSTANCE.toJsonString(user);
      byte[] encryptedJson = EncryptUtil.encryptDES(jsonString.getBytes(), SECURITY_SEED);
      return EncryptUtil.encryptBASE64(encryptedJson);
    } catch (Exception e) {
      // TODO
      throw new RuntimeException(e);
    }
  }
  
  @SuppressWarnings("unchecked")
  public TokenUserDetails getUserFromToken(String token) {
    try {
      byte[] decryptedBase64 = EncryptUtil.decryptBASE64(token);
      byte[] decryptedDES = EncryptUtil.decryptDES(decryptedBase64, SECURITY_SEED);
      // a little trick about construct the TokenUserDetails.
      Map<String, Object> map = (Map<String, Object>) JSONUtils.INSTANCE.toJaveObject(decryptedDES, Map.class);
      TokenUserDetails userDetails = new TokenUserDetails(map);
      if (null != userDetails) {
        // expired
        if (System.currentTimeMillis() > userDetails.getExpiration()) {
          userDetails = null;
        }
      }
      return userDetails;
    } catch (Exception e) {
      // TODO
      throw new RuntimeException(e);
    }
  }
}
