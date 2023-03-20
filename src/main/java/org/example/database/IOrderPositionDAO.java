package org.example.database;

import jdk.jshell.spi.ExecutionControl;
import org.example.model.Order;
import org.example.model.OrderPosition;

import java.util.List;
import java.util.Optional;

public interface IOrderPositionDAO {
    void persistOrderPosition(OrderPosition orderPosition, int orderId);
    List<OrderPosition> getOrderPositionByOrderId(int id) throws ExecutionControl.NotImplementedException;

    Optional<OrderPosition> getOrderPositionById(int id) throws ExecutionControl.NotImplementedException;
}
