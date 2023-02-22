package org.example.database;

import jdk.jshell.spi.ExecutionControl;
import org.example.model.Order;

import java.util.List;

public interface IOrderDAO {

    void persistOrder(Order order);
    void updateOrder(Order order) throws ExecutionControl.NotImplementedException;
    List<Order> getOrdersByUserId(int userId);


}
