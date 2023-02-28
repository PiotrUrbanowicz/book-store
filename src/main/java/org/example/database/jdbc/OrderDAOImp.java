package org.example.database.jdbc;

import jdk.jshell.spi.ExecutionControl;
import org.example.database.IOrderDAO;
import org.example.database.IOrderPositionDAO;
import org.example.model.Order;
import org.example.model.OrderPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrderDAOImp implements IOrderDAO {

    @Autowired
    Connection connection;

    @Autowired
    IOrderPositionDAO orderPositionDAO;

    @Override
    public void persistOrder(Order order) {
        try {
            String sql = "INSERT INTO torder (user_id, date, state, total) " +
                    "VALUES (?,?,?,?)";
            PreparedStatement ps = this.connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            //ps.setInt(1, order.getUserId());
            ps.setTimestamp(2, Timestamp.valueOf(order.getDate()));
            ps.setString(3, order.getState().toString());
            ps.setDouble(4, order.getTotal());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            order.setId(rs.getInt(1));

            for(OrderPosition orderPosition : order.getPositions()) {
                this.orderPositionDAO.persistOrderPosition(orderPosition, order.getId());
            }

            /*order.getPositions()
                    .forEach(op -> this.orderPositionDAO.persistOrderPosition(op, order.getId()));*/
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateOrder(Order order) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Nie potrzebna metoda !!");
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM torder WHERE user_id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("id");
//                result.add(new Order(
//                        orderId,
//                        rs.getInt("user_id"),
//                        this.orderPositionDAO.getOrderPositionByOrderId(orderId),
//                        rs.getTimestamp("date").toLocalDateTime(),
//                        Order.State.valueOf(rs.getString("state")),
//                        rs.getDouble("total")
//                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
