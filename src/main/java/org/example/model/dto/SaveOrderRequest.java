package org.example.model.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SaveOrderRequest {

    private int userId;
    private List<OrderPosition> positions = new ArrayList<>();
    private LocalDateTime date;
    private Order.State state;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class OrderPosition {

        private int bookId;
        private int quantity;

        public OrderPosition(int bookId, int quantity) {
            this.bookId = bookId;
            this.quantity = quantity;
        }
    }

}
