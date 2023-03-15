package org.example.services;

import jdk.jshell.spi.ExecutionControl;
import org.example.model.Order;
import org.example.model.dto.OrderDTO;
import org.example.model.dto.SaveOrderRequest;

import java.util.List;
import java.util.Optional;

public interface IOrderService {

    void confirmOrder();
    List<Order> getOrdersForCurrentUser();

   List<Order> getOrdersByUserId(int id);

    Order persistOrder(SaveOrderRequest orderRequest) throws ExecutionControl.NotImplementedException;

}
