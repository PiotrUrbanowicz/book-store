package org.example.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity(name="torderposition")
public class OrderPosition implements Saveable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
///zamówiona pozycja
    @ManyToOne(fetch = FetchType.EAGER)//tu był błąd - czemu nie zapisywał mi się order
    private Book book;

    private int quantity;

    public OrderPosition() {
    }


    public OrderPosition(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public OrderPosition(int id, Book book, int quantity) {
        this(book,quantity);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity(){
      this.quantity++;
    }
}
