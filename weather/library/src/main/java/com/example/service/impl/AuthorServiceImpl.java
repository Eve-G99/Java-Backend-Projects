package com.example.service.impl;

import com.example.dto.AuthorDTO;
import com.example.entity.Author;
import com.example.exception.AuthorNotFoundException;
import com.example.repository.AuthorRepository;
import com.example.repository.BookAuthorRepository;
import com.example.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookAuthorRepository bookAuthorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookAuthorRepository bookAuthorRepository){
        this.authorRepository = authorRepository;
        this.bookAuthorRepository = bookAuthorRepository;
    }

    // Create a new author
    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        Author savedAuthor = authorRepository.save(author);
        return convertToDTO(savedAuthor);
    }

    // Update an author
    @Override
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
        existingAuthor.setName(authorDTO.getName());
        Author updatedAuthor = authorRepository.save(existingAuthor);
        return convertToDTO(updatedAuthor);
    }


    // Retrieve all authors
    @Override
    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Retrieve an author by ID
    @Override
    public AuthorDTO getAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        return convertToDTO(author);
    }

    // get authors by name
    @Override
    public AuthorDTO findAuthorByName(String name) {
        Author author = authorRepository.findAuthorByName(name)
                .orElseThrow(() -> new AuthorNotFoundException(name));
        return convertToDTO(author);
    }

    // Delete an author by ID
    @Override
    public void deleteAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        bookAuthorRepository.deleteByAuthorId(author.getId());
        authorRepository.deleteById(authorId);
    }

    private AuthorDTO convertToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        return authorDTO;
    }


}
