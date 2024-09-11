package com.example.repository;

import com.example.entity.Author;
import com.example.repository.custom.CustomAuthorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Optional, but can be added for consistency
public interface AuthorRepository extends JpaRepository<Author, Long>, CustomAuthorRepository {

}
