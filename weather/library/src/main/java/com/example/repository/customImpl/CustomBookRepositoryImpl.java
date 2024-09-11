package com.example.repository.customImpl;

import com.example.entity.Book;
import com.example.repository.custom.CustomBookRepository;
import javax.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomBookRepositoryImpl implements CustomBookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findBooksByTitle(String title) {
        TypedQuery<Book> query = entityManager.createQuery(
                "SELECT b FROM Book b WHERE b.title = :title", Book.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    @Override
    public List<Book> findBooksByAuthorName(String authorName) {
        TypedQuery<Book> query = entityManager.createQuery(
                "SELECT b FROM Book b JOIN BookAuthor ba ON b.id = ba.bookId " +
                        "JOIN Author a ON ba.authorId = a.id WHERE a.name = :authorName", Book.class);
        query.setParameter("authorName", authorName);
        return query.getResultList();
    }

}
