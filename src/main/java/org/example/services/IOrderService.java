package org.example.services;

import org.example.model.Order;

import java.util.List;

public interface IOrderService {

    void confirmOrder();
    List<Order> getOrders();

}
