package org.example.sessionObject.services.impl;

import jakarta.annotation.Resource;
import org.example.database.IBookDAO;
import org.example.database.IOrderDAO;
import org.example.exceptions.NotEnoughBookException;
import org.example.model.Order;
import org.example.model.OrderPosition;
import org.example.sessionObject.services.ICartService;
import org.example.sessionObject.services.IOrderService;
import org.example.sessionObject.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

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
                new HashSet<>(orderPositions),//////////////////////////////
                LocalDateTime.now().withNano(0),
                Order.State.NEW,
                this.cartService.calculateCartSum());


        this.orderDAO.persistOrder(order);
        this.cartService.clearCart();

    }

    @Override
    public List<Order> getOrders() {
        return this.orderDAO.getOrdersByUserId(this.sessionObject.getUser().getId());
    }

}
