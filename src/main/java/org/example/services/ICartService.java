package org.example.services;

import org.example.model.OrderPosition;

import java.util.List;

public interface ICartService {
    void addBookToCart(int bookId);
    void clearCart();
    void removeFromCart(int bookId);

    List<OrderPosition> getCart();

    double calculateCartSum();



}
