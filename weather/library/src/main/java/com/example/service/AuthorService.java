package com.example.service;

import com.example.dto.AuthorDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {

    public AuthorDTO createAuthor(AuthorDTO authorDTO);
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO);
    public List<AuthorDTO> getAllAuthors();
    public AuthorDTO getAuthor(Long authorId);
    public AuthorDTO findAuthorByName(String name);
    public void deleteAuthor(Long authorId);
}
