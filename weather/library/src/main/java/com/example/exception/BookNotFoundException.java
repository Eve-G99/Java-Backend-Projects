package com.example.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String s) {
        super("No book found with: " + s);
    }

    public BookNotFoundException(Long id) {
        super("No book found with ID: " + id);
    }
}