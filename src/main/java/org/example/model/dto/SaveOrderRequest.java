package org.example.model.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.model.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaveOrderRequest {

    private int userId;
    private List<OrderPosition> positions=new ArrayList<>();
    private LocalDateTime date;
    private Order.State state;

    public SaveOrderRequest() {
    }

    public SaveOrderRequest(int userId, List<OrderPosition> positions, LocalDateTime date, Order.State state) {
        this.userId = userId;
        this.positions = positions;
        this.date = date;
        this.state = state;
    }

    public static class OrderPosition{

        private int bookId;
        private int quantity;

        public OrderPosition() {
        }

        public OrderPosition(int bookId, int quantity) {
            this.bookId = bookId;
            this.quantity = quantity;
        }

        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<OrderPosition> positions) {
        this.positions = positions;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Order.State getState() {
        return state;
    }

    public void setState(Order.State state) {
        this.state = state;
    }
}
