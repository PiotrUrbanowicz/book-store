package org.example.model.dto;

import jakarta.persistence.*;
import org.example.model.Book;
import org.example.model.OrderPosition;


public class OrderPositionDTO {

    private int id;
    private String book;
    private int quantity;

    public OrderPositionDTO() {
    }
    public OrderPositionDTO(OrderPosition orderPosition) {
        this.id=orderPosition.getId();
        this.book="http://localhost:8075/api/v1/book/"+orderPosition.getBook().getId();
        this.quantity=orderPosition.getQuantity();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
