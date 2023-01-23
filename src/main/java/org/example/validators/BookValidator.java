package org.example.validators;

import org.example.exceptions.BookValidationException;
import org.example.model.Book;


public class BookValidator {
    public static void validateTitle(String title) {
        String regex = "^[A-Z]{1}.*$";
        if(!title.matches(regex)) {
            throw new BookValidationException();
        }
    }

    public static void validateAuthor(String author) {
        String regex = "^[A-Z]{1}.*$";
        if(!author.matches(regex)) {
            throw new BookValidationException();
        }
    }

    public static void validateIsbn(String isbn) {
        String regex = "^(978|979){1}-[0-9]{2}-[0-9]{2,6}-[0-9]{1,5}-[0-9]{1}$";
        if(!isbn.matches(regex)) {
            throw new BookValidationException();
        }
    }

    public static void validateBook(Book book) {
        validateTitle(book.getTitle());
        validateAuthor(book.getAuthor());
        validateIsbn(book.getIsbn());
    }
}
