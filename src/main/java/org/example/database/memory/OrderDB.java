package org.example.database.memory;

import org.example.database.IOrderDAO;
import org.example.model.Order;
import org.example.database.sequence.IIdSequence;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDB implements IOrderDAO {

    private final List<Order> orders=new ArrayList<>();

    @Autowired
    IIdSequence orderSequence;

    @Override
    public void persistOrder(Order order) {
        order.setId(this.orderSequence.getId());
        orders.add(order);
    }

    @Override
    public void updateOrder(Order order) {//nie pętlą for bo nie może się zminiać lista w trakcie jej przeglądania, możliwość błędu
        Iterator<Order> iterator = this.orders.iterator();
        while(iterator.hasNext()) {
            Order orderFromDb = iterator.next();
            if(orderFromDb.getId() == order.getId()) {
                iterator.remove();
                this.orders.add(order);
                break;
            }
        }
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
//        List<Order> result = new ArrayList<>();
//        for(Order order : this.orders) {
////            if(order.getUserId() == userId) {
////                result.add(order);
////            }
//        }
//        return result;

//    return this.orders.stream()
//            .filter(order->order.getUserId()==userId)
//            .toList();

        return null;

    }


}
