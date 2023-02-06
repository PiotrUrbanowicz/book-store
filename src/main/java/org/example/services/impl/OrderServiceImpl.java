package org.example.services.impl;

import jakarta.annotation.Resource;
import org.example.database.IBookDAO;
import org.example.database.IOrderDAO;
import org.example.exceptions.NotEnoughBookException;
import org.example.model.Order;
import org.example.model.OrderPosition;
import org.example.services.ICartService;
import org.example.services.IOrderService;
import org.example.sessionObject.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    ICartService cartService;

    @Autowired
    IOrderDAO orderDAO;

    @Override
    public void confirmOrder() {
        Collection<OrderPosition> orderPositions=this.sessionObject.getCart().values();
        for (OrderPosition orderPosition:orderPositions) {
            int quantityAfterOrder = orderPosition.getBook().getQuantity()-orderPosition.getQuantity();

            if(quantityAfterOrder>=0) {
                orderPosition.getBook().setQuantity(quantityAfterOrder);
            }else{
                throw new NotEnoughBookException();
            }
        }
        Order order = new Order(this.sessionObject.getUser().getId(),
                new ArrayList<>(orderPositions),
                LocalDateTime.now(),
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
