package com.elminster.grs.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.elminster.grs.web.service.GameCollectionService;
import com.elminster.grs.web.vo.response.JsonResponseTemplate;
import com.elminster.spring.security.domain.User;
import com.elminster.web.commons.request.Option;
import com.elminster.web.commons.request.OptionFactory;
import com.elminster.web.commons.request.Order;
import com.elminster.web.commons.request.OrderChain;
import com.elminster.web.commons.response.JsonResponse;

/**
 * The collection controller.
 * 
 * @author jgu
 * @version 1.0
 */
@RestController
@RequestMapping("/v1.0/collection")
public class CollectionController extends BaseController {
  
  //@Autowired
  private GameCollectionService collectionService;

  @RequestMapping(value = "/current", method = RequestMethod.GET)
  public @ResponseBody JsonResponse getCurrentUserCollection(HttpServletRequest request, HttpServletResponse response)
      throws IOException, Exception {
    User currentUser = this.getCurrentAuthUser();
    int userId = currentUser.getId();
    Option option = OptionFactory.INSTANCE.getOption(request);
    if (null == option.getOrderChain()) {
      // set default order
      OrderChain oc = new OrderChain();
      oc.addOrder(new Order("lastPlayedDate"));
      option.setOrderChain(oc);
    }
    JsonResponse jsonResponse =  getUserCollectionByUserId(userId, option);
    return jsonResponse;
  }

  // ========================================================================
  public JsonResponse getUserCollectionByUserId(final int userId, final Option option) throws IOException, Exception {
    JsonResponse jsonResponse = new JsonResponseTemplate() {
      protected Object callback() throws Exception {
        return collectionService.getUsersGameCollectionInfo(userId, option);
      }
    }.getJsonResponse();
    return jsonResponse;
  }
}
