package com.elminster.grs.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.elminster.grs.giantbomb.util.ApiCounter;
import com.elminster.grs.giantbomb.util.ApiCounterManager;
import com.elminster.web.commons.response.JsonResponse;

@RestController
@RequestMapping("/v1.0/sysAdmin")
public class SystemAdminController extends BackendController {

  public @ResponseBody JsonResponse getApiCounterStatus() {
    Collection<ApiCounter> collection = ApiCounterManager.INSTANCE.listApiCounters();
    List<String> status = new ArrayList<>(collection.size());
    for (ApiCounter c : collection) {
      status.add(c.toString());
    }
    return new JsonResponse().setData(status);
  }
}
