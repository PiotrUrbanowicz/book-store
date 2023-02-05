package org.example.database;

import org.example.model.Order;

import java.util.List;

public interface IOrderDAO {

    void persistOrder(Order order);
    void updateOrder(Order order);
    List<Order> getOrdersByUserId(int userId);


}
