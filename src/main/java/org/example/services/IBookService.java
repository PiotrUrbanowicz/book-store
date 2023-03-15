package org.example.services;

import org.example.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> getBooks();

    List<Book> getBooksByPattern(String pattern);

    Optional<Book> getBookById(int id);

    void updateBook(Book book, int id);

    void persistBook(Book book);
}
