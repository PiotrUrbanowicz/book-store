package org.example.model.dto;


import org.example.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private final List<Order> orders=new ArrayList<>();

    public List<Order> getOrders(){
        return orders;
    }

}
