package org.example.sessionObject.services;

import org.example.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> getBooks();

    Optional<Book> getBookById(int id);

    void updateBook(Book book, int id);

    void persistBook(Book book);
}
