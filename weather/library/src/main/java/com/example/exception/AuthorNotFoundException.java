package com.example.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String s) {
        super("No author found with: " + s);
    }

    public AuthorNotFoundException(Long id) {
        super("No author found with: " + id);

    }
}
