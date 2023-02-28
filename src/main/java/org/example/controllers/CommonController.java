package org.example.controllers;


import jakarta.annotation.Resource;
import org.example.sessionObject.services.IBookService;
import org.example.sessionObject.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class CommonController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IBookService BookServiceImpl;


    @RequestMapping(value="/main", method= RequestMethod.GET)
    public String main(){
        return "redirect:/";
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String main(Model model){
        model.addAttribute("books",this.BookServiceImpl.getBooks());
        model.addAttribute("sessionObject",this.sessionObject);
        return "main";
    }

    @RequestMapping(value="/", method= RequestMethod.POST)
    public String main(@RequestParam String pattern){
        this.sessionObject.setPattern(pattern);
        return "redirect:/";
    }


    @RequestMapping(value="/contact", method= RequestMethod.GET)
    public String contact(Model model){
        model.addAttribute("sessionObject",this.sessionObject);
        return "contact";
    }

}
