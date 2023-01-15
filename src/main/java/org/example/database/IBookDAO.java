package org.example.database;

import org.example.model.Book;

import java.util.List;

public interface IBookDAO {
    public List<Book> getBooks();

    public List<Book> getBooksByPattern(String pattern);
}
