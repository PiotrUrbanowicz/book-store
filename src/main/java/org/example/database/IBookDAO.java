package org.example.database;

import org.example.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    List<Book> getBooks();

    List<Book> getBooksByPattern(String pattern);

    Optional<Book> getBookById(int id);

    void persistBook(Book book);

    void updateBook(Book book);
}
