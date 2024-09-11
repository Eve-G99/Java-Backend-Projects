package com.example.controller;

import com.example.dto.AuthorDTO;
import com.example.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    // Create a new author
    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        AuthorDTO createdAuthor = authorService.createAuthor(authorDTO);
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    // Update an author
    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO) {
        AuthorDTO updatedAuthor = authorService.updateAuthor(id, authorDTO);
        return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
    }

    // Retrieve all authors
    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<AuthorDTO> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    // Retrieve an author by ID
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable Long id) {
        AuthorDTO author = authorService.getAuthor(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    // Retrieve an author by name
    @GetMapping("/byName")
    public ResponseEntity<AuthorDTO> findAuthorByName(@RequestParam String name) {
        AuthorDTO author = authorService.findAuthorByName(name);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    // Delete an author by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>("Author with ID " + id + " has been deleted successfully", HttpStatus.OK);
    }


}
