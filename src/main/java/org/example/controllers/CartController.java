package org.example.controllers;

import jakarta.annotation.Resource;
import org.example.exceptions.NotEnoughBookException;
import org.example.services.ICartService;
import org.example.sessionObject.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CartController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    ICartService cartService;

    @RequestMapping(path = "/cart", method = RequestMethod.GET)
    public String cart(Model model){
        model.addAttribute("sessionObject",this.sessionObject);
        model.addAttribute("cart",this.cartService.getCart());
        model.addAttribute("sum",this.cartService.calculateCartSum());
        return "cart";
    }

    @RequestMapping(path = "/cart/add/{bookId}", method = RequestMethod.GET)
    public String addBookCart(@PathVariable int bookId){
        try {
            this.cartService.addBookToCart(bookId);
        }catch (NotEnoughBookException e){
            this.sessionObject.setInfo("Nie ma już więcej egzemplarzy !!!");
            return "redirect:/";
        }

        return "redirect:/";
    }

    @RequestMapping(path="/cart/remove/{bookId}",method = RequestMethod.GET)
    public String deleteCart(@PathVariable int bookId){
        this.cartService.removeFromCart(bookId);
        return "redirect:/cart";
    }




}
