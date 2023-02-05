package org.example.controllers;

import jakarta.annotation.Resource;
import org.example.model.OrderPosition;
import org.example.services.IOrderService;
import org.example.sessionObject.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class OrderController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IOrderService orderService;

    @RequestMapping(path="/order/confirm")
    public String orderConfirm(){

        this.orderService.confirmOrder();
        return "redirect:/";
    }

}
