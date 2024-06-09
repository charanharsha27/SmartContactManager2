package com.scm.SmartContactManager.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.SmartContactManager.dao.OrderRepository;
import com.scm.SmartContactManager.entities.Orders;

@Service
public class IOrderServiceImpl implements IOrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Orders saveOrder(Orders orders) {
        return orderRepository.save(orders);
    }

    @Override
    public Orders getOrder(String orderId) {
        return orderRepository.getOrdersByOrderId(orderId);
    }
}