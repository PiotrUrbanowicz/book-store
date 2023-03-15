package org.example.controllers.rest;



import jdk.jshell.spi.ExecutionControl;
import org.example.database.IBookDAO;
import org.example.database.IUserDAO;
import org.example.exceptions.PersistOrderException;
import org.example.model.Book;
import org.example.model.Order;
import org.example.model.OrderPosition;
import org.example.model.User;
import org.example.model.dto.OrderDTO;
import org.example.model.dto.SaveOrderRequest;
import org.example.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/order")
public class RestApiOrderController {

    @Autowired
    IOrderService orderService;

    @Autowired
    IUserDAO userDAO;

    @Autowired
    IBookDAO bookDAO;

    @RequestMapping(path="",method = RequestMethod.GET)
    public OrderDTO getOrdersByUserId(@RequestParam int userId){
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.getOrders().addAll(this.orderService.getOrdersByUserId(userId));
        return orderDTO;
    }

    @RequestMapping(path="",method = RequestMethod.POST)
    public ResponseEntity<Order> persistOrder(@RequestBody SaveOrderRequest orderRequest) {
        try {
            Order order = this.orderService.persistOrder(orderRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(order);
        } catch (ExecutionControl.NotImplementedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }catch(PersistOrderException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }






}
