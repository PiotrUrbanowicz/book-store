package org.example.controllers;

import jakarta.annotation.Resource;
import org.example.exceptions.BookValidationException;
import org.example.model.Book;
import org.example.services.IBookService;
import org.example.sessionObject.SessionObject;
import org.example.validators.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller

public class BookController {

    @Resource
    SessionObject sessionObject;
    @Autowired
    IBookService bookService;

    @RequestMapping(path="/book/add", method = RequestMethod.GET)
    public String addBook(Model model){
        model.addAttribute("sessionObject",this.sessionObject);
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @RequestMapping(path="/book/add", method =RequestMethod.POST)
    public String addBook(@ModelAttribute Book book){
        try {
            BookValidator.validateBook(book);
        } catch (BookValidationException e) {
            e.printStackTrace();
            return "redirect:/book/add";
        }
        this.bookService.persistBook(book);
        return "redirect:/";
    }
    @RequestMapping(path = "/book/edit/{bookId}", method = RequestMethod.GET)
    public String edit(@PathVariable int bookId, Model model){
        Optional<Book> bookBox=bookService.getBookById(bookId);
        if(bookBox.isEmpty()){
            return "redirect:/";
        }
        model.addAttribute("sessionObject",this.sessionObject);
        model.addAttribute("book",bookBox.get());
        return "book-form";
    }

    @RequestMapping(path="book/edit/{bookId}", method = RequestMethod.POST)
    public String edit(@ModelAttribute Book book,
                       @PathVariable int bookId){
        try {
            BookValidator.validateBook(book);
        } catch (BookValidationException e) {
            e.printStackTrace();
            return "redirect:/book/edit/"+bookId;
        }
        this.bookService.updateBook(book,bookId);
        return "redirect:/";




    }




}
