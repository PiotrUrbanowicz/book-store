package org.example.model.dto;

import jakarta.persistence.*;
import org.example.model.Order;
import org.example.model.OrderPosition;
import org.example.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private int id;
    private String user;
    private List<String> positions = new ArrayList<>();
    private LocalDateTime date;
    private Order.State state;
    private double total;

    public OrderDTO() {
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        if(order.getUser() != null) {
            this.user = "http://localhost:8075/api/v1/user/" + order.getUser().getId();
        }
        this.date = order.getDate();
        this.state = order.getState();
        this.total = order.getTotal();
        this.positions =order.getPositions().stream()
                .map(op->"http://localhost:8075/api/v1/orderposition/"+op.getId())
                .toList();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<String> getPositions() {
        return positions;
    }

    public void setPositions(List<String> positions) {
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "user='" + user + '\'' +
                ", positions=" + positions +
                ", date=" + date +
                ", state=" + state +
                ", total=" + total +
                '}';
    }
}
