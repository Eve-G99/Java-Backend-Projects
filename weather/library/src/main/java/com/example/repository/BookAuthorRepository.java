package com.example.repository;

import com.example.entity.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {
    void deleteByBookId(Long bookId);
    void deleteByAuthorId(Long authorId);
    List<BookAuthor> findByBookId(Long bookId);
}
