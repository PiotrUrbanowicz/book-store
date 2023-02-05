package org.example.model;

public class OrderPosition {

    private Book book;

    private int quantity;

    public OrderPosition() {
    }

    public OrderPosition(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
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
