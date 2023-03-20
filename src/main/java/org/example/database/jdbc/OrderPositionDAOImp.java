package org.example.database.jdbc;

import jdk.jshell.spi.ExecutionControl;
import org.example.database.IBookDAO;
import org.example.database.IOrderPositionDAO;
import org.example.model.Book;
import org.example.model.Order;
import org.example.model.OrderPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class OrderPositionDAOImp implements IOrderPositionDAO {

    @Autowired
    Connection connection;
    @Autowired
    IBookDAO bookDAO;
    @Override
    public void persistOrderPosition(OrderPosition orderPosition, int orderId) {
        try {
            String sql = "INSERT INTO torderposition (book_id, order_id, quantity) " +
                    "VALUES (?, ?, ?);";
            PreparedStatement ps = this.connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, orderPosition.getBook().getId());
            ps.setInt(2, orderId);
            ps.setInt(3, orderPosition.getQuantity());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            orderPosition.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderPosition> getOrderPositionByOrderId(int id) {
        List<OrderPosition> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM torderposition WHERE order_id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Optional<Book> bookBox = this.bookDAO
                        .getBookById(rs.getInt("book_id"));
                if(bookBox.isPresent()) {
                    result.add(new OrderPosition(
                            rs.getInt("id"),
                            bookBox.get(),
                            rs.getInt("quantity")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Optional<OrderPosition> getOrderPositionById(int id) throws ExecutionControl.NotImplementedException {
      throw new ExecutionControl.NotImplementedException("Not Implemented");
    }
}
