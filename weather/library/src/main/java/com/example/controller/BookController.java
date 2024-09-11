package com.example.controller;

import com.example.dto.BookDTO;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping()
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        BookDTO createdBook = bookService.createBook(bookDTO);
        return new ResponseEntity<>(createdBook, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookService.updateBook(id, bookDTO);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }



    // Get all books
    @GetMapping()
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBooksById(@PathVariable Long id) {
        BookDTO bookDTO = bookService.getBookById(id);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @GetMapping("/byTitle")
    public ResponseEntity<List<BookDTO>> getBooksByTitle(@RequestParam String title) {
        List<BookDTO> books = bookService.getBooksByTitle(title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Get books by author name
    @GetMapping("/byAuthor")
    public ResponseEntity<List<BookDTO>> getBooksByAuthorName(@RequestParam String authorName) {
        List<BookDTO> books = bookService.getBooksByAuthorName(authorName);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Delete a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>("Book with ID " + id + " has been deleted successfully", HttpStatus.OK);
    }


}
