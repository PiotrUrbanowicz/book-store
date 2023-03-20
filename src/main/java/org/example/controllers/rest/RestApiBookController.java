package org.example.controllers.rest;

import org.example.model.Book;
import org.example.model.dto.BooksDTO;
import org.example.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/book")
public class RestApiBookController {

    @Autowired
    IBookService bookService;

    @RequestMapping(path="",method = RequestMethod.GET)
    public BooksDTO getBooks(@RequestParam(required = false) String pattern){
        BooksDTO response=new BooksDTO();
        if(pattern==null) {
            response.getBooks().addAll(bookService.getBooks());
        }else{
            response.getBooks().addAll(bookService.getBooksByPattern(pattern));
        }
        return response;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Book> persistBook(@RequestBody Book book) {
            this.bookService.persistBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(book);
        }

        @RequestMapping(path="/{bookId}",method=RequestMethod.GET)
    public ResponseEntity<Book> getBookById(@PathVariable int bookId){
        Optional<Book> bookBox=this.bookService.getBookById(bookId);

        return (bookBox.isEmpty())?
                ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                :ResponseEntity.status(HttpStatus.OK).body(bookBox.get());
        }

        @RequestMapping(path="/{bookId}",method =RequestMethod.PUT)
    public Book editBook(@PathVariable int bookId,
                         @RequestBody Book book){
        this.bookService.updateBook(book,bookId);
            return book;

        }//może tak być bo??
    //a co jeśli będzie błąd


}
