package com.example.repository.custom;

import com.example.entity.Author;

import java.util.Optional;

public interface CustomAuthorRepository {
    Optional<Author> findAuthorByName(String name);
}
