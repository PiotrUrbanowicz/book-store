package org.example.model.dto;


import org.example.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrdersDTO {

    private final List<OrderDTO> orders=new ArrayList<>();

    public List<OrderDTO> getOrders(){
        return orders;
    }

}
