package com.example.repository.custom;

import com.example.entity.Book;

import java.util.List;

public interface CustomBookRepository {
    List<Book> findBooksByTitle(String title);
    List<Book> findBooksByAuthorName(String authorName);
}
