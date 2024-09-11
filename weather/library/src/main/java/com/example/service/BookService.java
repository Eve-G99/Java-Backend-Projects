package com.example.service;

import com.example.dto.BookDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {


    public BookDTO createBook(BookDTO bookDTO);
    public BookDTO updateBook(Long id, BookDTO bookDTO);
    public List<BookDTO> getAllBooks();
    public BookDTO getBookById(Long id);
    public List<BookDTO> getBooksByTitle(String title);
    public List<BookDTO> getBooksByAuthorName(String authorName);
    public void deleteBook(Long bookId);

}
