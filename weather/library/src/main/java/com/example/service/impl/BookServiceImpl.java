package com.example.service.impl;

import com.example.dto.BookDTO;
import com.example.entity.Author;
import com.example.entity.Book;
import com.example.entity.BookAuthor;
import com.example.exception.BookNotFoundException;
import com.example.repository.AuthorRepository;
import com.example.repository.BookAuthorRepository;
import com.example.repository.BookRepository;
import com.example.service.BookService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookAuthorRepository bookAuthorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, BookAuthorRepository bookAuthorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookAuthorRepository = bookAuthorRepository;
    }


    @Transactional
    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        bookRepository.save(book);

        for (String authorName : bookDTO.getAuthorNames()) {
            Author author = authorRepository.findAuthorByName(authorName)
                    .orElseGet(() -> {
                        Author newAuthor = new Author();
                        newAuthor.setName(authorName);
                        return authorRepository.save(newAuthor);
                    });

            BookAuthor bookAuthor = new BookAuthor();
            bookAuthor.setBookId(book.getId());
            bookAuthor.setAuthorId(author.getId());
            bookAuthorRepository.save(bookAuthor);
        }

        return convertToDTO(book, bookDTO.getAuthorNames());
    }



    // Update a book with associated authors
    @Transactional
    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        existingBook.setTitle(bookDTO.getTitle());
        bookRepository.save(existingBook);

        // Remove old BookAuthor entries
        bookAuthorRepository.deleteByBookId(existingBook.getId());

        // Add new BookAuthor entries
        for (String authorName : bookDTO.getAuthorNames()) {
            Author author = authorRepository.findAuthorByName(authorName)
                    .orElseGet(() -> {
                        Author newAuthor = new Author();
                        newAuthor.setName(authorName);
                        return authorRepository.save(newAuthor);
                    });

            BookAuthor bookAuthor = new BookAuthor();
            bookAuthor.setBookId(existingBook.getId());
            bookAuthor.setAuthorId(author.getId());
            bookAuthorRepository.save(bookAuthor);
        }

        return convertToDTO(existingBook, bookDTO.getAuthorNames());
    }


    // Get all books
    @Transactional
    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> {
                    List<String> authorNames = bookAuthorRepository.findByBookId(book.getId())
                            .stream()
                            .map(bookAuthor -> authorRepository.findById(bookAuthor.getAuthorId()).orElseThrow().getName())
                            .collect(Collectors.toList());
                    return convertToDTO(book, authorNames);
                })
                .collect(Collectors.toList());
    }

    // Retrieve a book by its ID
    @Transactional
    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        List<String> authorNames = bookAuthorRepository.findByBookId(book.getId())
                .stream()
                .map(bookAuthor -> authorRepository.findById(bookAuthor.getAuthorId()).orElseThrow().getName())
                .collect(Collectors.toList());

        return convertToDTO(book, authorNames);
    }


    // Get books by title
    @Transactional
    @Override
    public List<BookDTO> getBooksByTitle(String title) {
        List<Book> books = bookRepository.findBooksByTitle(title);

        // Check if the list is empty and throw exception
        if (books.isEmpty()) {
            throw new BookNotFoundException(title);
        }

        return books.stream()
                .map(book -> {
                    List<String> authorNames = bookAuthorRepository.findByBookId(book.getId())
                            .stream()
                            .map(bookAuthor -> authorRepository.findById(bookAuthor.getAuthorId()).orElseThrow().getName())
                            .collect(Collectors.toList());
                    return convertToDTO(book, authorNames);
                })
                .collect(Collectors.toList());
    }

    // Get books by author name using custom repository method
    public List<BookDTO> getBooksByAuthorName(String authorName) {
        List<Book> books = bookRepository.findBooksByAuthorName(authorName);

        // Check if the list is empty and return empty list
        if (books.isEmpty()) {
            throw new BookNotFoundException(authorName);
        }
        return books.stream()
                .map(book -> {
                    List<String> authorNames = bookAuthorRepository.findByBookId(book.getId())
                            .stream()
                            .map(bookAuthor -> authorRepository.findById(bookAuthor.getAuthorId()).orElseThrow().getName())
                            .collect(Collectors.toList());
                    return convertToDTO(book, authorNames);
                })
                .collect(Collectors.toList());
    }

    // Delete a book and its associations
    @Transactional
    @Override
    public void deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        bookAuthorRepository.deleteByBookId(book.getId());
        bookRepository.deleteById(bookId);
    }

    private BookDTO convertToDTO(Book book, List<String> authorNames) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthorNames(authorNames);
        return bookDTO;
    }
}
