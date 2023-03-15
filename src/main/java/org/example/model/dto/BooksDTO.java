package org.example.model.dto;

import org.example.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BooksDTO {
    private final List<Book> books=new ArrayList<>();

    public List<Book> getBooks(){
        return books;
    }
}
