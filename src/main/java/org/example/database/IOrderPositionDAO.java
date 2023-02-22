package org.example.database;

import org.example.model.OrderPosition;

import java.util.List;

public interface IOrderPositionDAO {
    void persistOrderPosition(OrderPosition orderPosition, int orderId);
    List<OrderPosition> getOrderPositionByOrderId(int id);
}
