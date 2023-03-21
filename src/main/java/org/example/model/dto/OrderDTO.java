package org.example.model.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.Order;
import org.example.model.OrderPosition;
import org.example.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

    private int id;
    private String user;
    private List<String> positions = new ArrayList<>();
    private LocalDateTime date;
    private Order.State state;
    private double total;

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

}
