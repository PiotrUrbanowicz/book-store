package org.example.model.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.Book;
import org.example.model.OrderPosition;

@Getter
@Setter
@NoArgsConstructor
public class OrderPositionDTO {

    private int id;
    private String book;
    private int quantity;

    public OrderPositionDTO(OrderPosition orderPosition) {
        this.id=orderPosition.getId();
        this.book="http://localhost:8075/api/v1/book/"+orderPosition.getBook().getId();
        this.quantity=orderPosition.getQuantity();
    }


}
