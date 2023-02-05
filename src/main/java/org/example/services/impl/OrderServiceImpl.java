package org.example.services.impl;

import jakarta.annotation.Resource;
import org.example.database.IBookDAO;
import org.example.database.IOrderDAO;
import org.example.model.Order;
import org.example.model.OrderPosition;
import org.example.services.ICartService;
import org.example.services.IOrderService;
import org.example.sessionObject.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

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
        Collection<OrderPosition> orderPositions=sessionObject.getCart().values();
        for (OrderPosition orderPosition:orderPositions) {
            int quantityAfterOrder = orderPosition.getBook().getQuantity()-orderPosition.getQuantity();

            if(quantityAfterOrder>=0) {
                orderPosition.getBook().setQuantity(quantityAfterOrder);
            }else{
                //TODO: ktoś kupił za dużo sztuk danej pozycji
            }
        }
        Order order = new Order(this.sessionObject.getUser().getId(),
                new ArrayList<>(orderPositions),
                //LocalDateTime.now(),
                Order.State.NEW,
                this.cartService.calculateCartSum());

        this.orderDAO.persistOrder(order);

        //TODO: zablokowanie dodawania ilości książek większej niż na stanie
        this.cartService.clearCart();

    }

}
