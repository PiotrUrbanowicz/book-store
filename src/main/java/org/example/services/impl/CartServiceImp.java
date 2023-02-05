package org.example.services.impl;

import jakarta.annotation.Resource;
import org.example.database.IBookDAO;
import org.example.model.Book;
import org.example.model.OrderPosition;
import org.example.services.ICartService;
import org.example.sessionObject.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartServiceImp implements ICartService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IBookDAO bookDAO;



    @Override
    public void addBookToCart(int bookId) {

        Map<Integer,OrderPosition> cart=this.sessionObject.getCart();
        Optional<Book> bookBox =this.bookDAO.getBookById(bookId);
        if(bookBox.isEmpty()){
            return;
        }

        if(cart.get(bookId)==null){
            cart.put(bookId,new OrderPosition(bookBox.get(),1));
        }else{
            cart.get(bookId).incrementQuantity();
        }
    }



    @Override
    public void clearCart() {
        this.sessionObject.getCart().clear();
    }

    @Override
    public void removeFromCart(int bookId) {
        Map<Integer,OrderPosition> cart=this.sessionObject.getCart();
        cart.remove(bookId);

    }

    @Override
    public List<OrderPosition> getCart() {

        return new ArrayList<>(this.sessionObject.getCart().values());
    }

    @Override
    public double calculateCartSum() {
        double sum=0;
        for (OrderPosition orderPosition: this.getCart()) {
            sum+=orderPosition.getBook().getPrice()*orderPosition.getQuantity();
        }
        return sum;
    }


}
