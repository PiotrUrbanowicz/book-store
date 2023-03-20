package org.example.services.impl;

import jakarta.annotation.Resource;
import jdk.jshell.spi.ExecutionControl;
import org.example.database.IBookDAO;
import org.example.database.IOrderDAO;
import org.example.database.IUserDAO;
import org.example.exceptions.NotEnoughBookException;
import org.example.exceptions.PersistOrderException;
import org.example.model.Book;
import org.example.model.Order;
import org.example.model.OrderPosition;
import org.example.model.User;
import org.example.model.dto.OrderDTO;
import org.example.model.dto.SaveOrderRequest;
import org.example.services.ICartService;
import org.example.services.IOrderService;
import org.example.model.sessionObject.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    ICartService cartService;

    @Autowired
    IOrderDAO orderDAO;
    @Autowired
    IBookDAO bookDAO;

    @Autowired
    IUserDAO userDAO;

    @Override
    public void confirmOrder() {
        Collection<OrderPosition> orderPositions=this.sessionObject.getCart().values();
        for (OrderPosition orderPosition:orderPositions) {
            int quantityAfterOrder = orderPosition.getBook().getQuantity()-orderPosition.getQuantity();

            if(quantityAfterOrder<0) {
                throw new NotEnoughBookException();
            }
            orderPosition.getBook().setQuantity(quantityAfterOrder);
            this.bookDAO.updateBook(orderPosition.getBook());
        }


//        Order order = new Order(this.sessionObject.getUser().getId(),
//                new ArrayList<>(orderPositions),
//                LocalDateTime.now().withNano(0),
//                Order.State.NEW,
//                this.cartService.calculateCartSum());

        //this.sessionObject.getUser().setName("koty");
        Order order=new Order(this.sessionObject.getUser(),
                new ArrayList<>(orderPositions),//////////////////////////////
                LocalDateTime.now().withNano(0),
                Order.State.NEW,
                this.cartService.calculateCartSum());


        this.orderDAO.persistOrder(order);
        this.cartService.clearCart();

    }

    @Override
    public List<Order> getOrdersForCurrentUser() {
        return this.orderDAO.getOrdersByUserId(this.sessionObject.getUser().getId());
    }

    @Override
    public List<Order> getOrdersByUserId(int id) {
        return this.orderDAO.getOrdersByUserId(id)
                .stream()
                .peek(o->o.getUser().setOrders(null))
                .peek(o->o.getUser().setPassword("****"))
                .toList();
    }

    @Override
    public Order persistOrder(SaveOrderRequest orderRequest) throws ExecutionControl.NotImplementedException {

        Optional<User> userBox=this.userDAO.getUserById(orderRequest.getUserId());
        if(userBox.isEmpty()){
            throw new PersistOrderException();
        }
        Order order=new Order();
        order.setUser(userBox.get());


        for (SaveOrderRequest.OrderPosition element:orderRequest.getPositions()) {
            Optional<Book> bookBox=this.bookDAO.getBookById(element.getBookId());
            if(bookBox.isEmpty()){
                throw new PersistOrderException();
            }
            OrderPosition op=new OrderPosition();
            op.setBook(bookBox.get());
            op.setQuantity(element.getQuantity());

            order.getPositions().add(op);
        }
        order.setDate(orderRequest.getDate());
        order.setState(orderRequest.getState());
        order.setTotal(
                order.getPositions().stream()
                        .mapToDouble(op->op.getBook().getPrice()*op.getQuantity())
                        .sum());

        userBox.get().getOrders().add(order);
        this.orderDAO.persistOrder(order);
       // this.userDAO.updateUser(userBox.get());

        userBox.get().setOrders(null);
        userBox.get().setPassword("*****");

        return order;
    }

    @Override
    public Optional<Order> getOrderById(int orderId) throws ExecutionControl.NotImplementedException {
        return this.orderDAO.getOrderById(orderId);
    }


}
