package com.scm.SmartContactManager.service;

import com.scm.SmartContactManager.entities.Orders;

public interface IOrderService {

    Orders saveOrder(Orders orders);

    Orders getOrder(String orderId);

}