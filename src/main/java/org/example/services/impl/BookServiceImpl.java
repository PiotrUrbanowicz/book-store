package org.example.services.impl;

import jakarta.annotation.Resource;
import org.example.database.IBookDAO;
import org.example.model.Book;
import org.example.services.IBookService;
import org.example.sessionObject.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookServiceImpl implements IBookService {
    @Resource
    SessionObject sessionObject;
    @Autowired
    IBookDAO bookDAO;

    @Override
    public List<Book> getBooks() {
        Optional<String> patternBox = this.sessionObject.getPattern();
        if (patternBox.isEmpty()) {
            return this.bookDAO.getBooks();
        } else {
            return this.bookDAO.getBooksByPattern(patternBox.get());
        }
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return this.bookDAO.getBookById(id);
    }

    @Override
    public void updateBook(Book book, int id) {
        book.setId(id);
        this.bookDAO.updateBook(book);
    }

    @Override
    public void persistBook(Book book) {
        this.bookDAO.persistBook(book);
    }


}


