package com.elminster.grs.web.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.elminster.web.commons.request.Option;
import com.elminster.web.commons.request.OrderChain;
import com.elminster.web.commons.request.Paging;

final public class OptionUtil {

  public static Sort getSort(Option option) {
    Sort sort = null;
    if (null != option) {
      OrderChain orderChain = option.getOrderChain();
      if (null != orderChain) {
        List<Order> orders = new ArrayList<Order>();
        for (com.elminster.web.commons.request.Order o : orderChain) {
          Order order = new Order(
              o.getDirection() == com.elminster.web.commons.request.Order.Direction.DESC ? Direction.DESC : Direction.ASC,
                  o.getKey());
          orders.add(order);
        }
        sort = new Sort(orders);
      }
    }
    return sort;
  }
  
  public static Pageable getPageable(Option option) {
    Pageable pageable = null;
    if (null != option) {
      Paging paging = option.getPaging();
      if (null != paging) {
        Sort sort = getSort(option);
        pageable = new PageRequest(paging.getPageNumber(), paging.getPageSize(), sort);
      }
    }
    return pageable;
  }
}
