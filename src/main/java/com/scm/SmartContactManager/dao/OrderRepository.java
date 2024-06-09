package com.scm.SmartContactManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.SmartContactManager.entities.Orders;

public interface OrderRepository extends JpaRepository<Orders,Long>{

    public Orders getOrdersByOrderId(String orderId);


}
