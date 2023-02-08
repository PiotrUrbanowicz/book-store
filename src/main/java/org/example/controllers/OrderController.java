package org.example.controllers;

import jakarta.annotation.Resource;
import org.example.exceptions.NotEnoughBookException;
import org.example.services.IOrderService;
import org.example.sessionObject.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IOrderService orderService;


    @RequestMapping(path = "/order/confirm")
    public String orderConfirm() {
        try {
            this.orderService.confirmOrder();
        } catch (NotEnoughBookException e) {
            //informacje z modelu żyją tylko dla tego widoku a więc gdy przekierowójemy na cart to odpalamy nowy widok
            //nowy controller nowa funkcja i ten model by się usunął
            this.sessionObject.setInfo("Niepoprawna ilość produktów !!");
            return "redirect:/cart";
        }
        return "redirect:/cart";
    }

    @RequestMapping(path = "/order")
    public String order(Model model) {
        model.addAttribute("sessionObject", this.sessionObject);
        model.addAttribute("orders",this.orderService.getOrders());
        return "orders";
    }


}
