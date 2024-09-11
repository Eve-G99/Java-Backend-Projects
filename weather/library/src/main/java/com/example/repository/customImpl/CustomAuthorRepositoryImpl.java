package com.example.repository.customImpl;

import com.example.entity.Author;
import com.example.repository.custom.CustomAuthorRepository;
import javax.persistence.*;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomAuthorRepositoryImpl implements CustomAuthorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Author> findAuthorByName(String name) {
        TypedQuery<Author> query = entityManager.createQuery(
                "SELECT a FROM Author a WHERE a.name = :name", Author.class);
        query.setParameter("name", name);
        List<Author> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(resultList.get(0));
        }
    }
}


