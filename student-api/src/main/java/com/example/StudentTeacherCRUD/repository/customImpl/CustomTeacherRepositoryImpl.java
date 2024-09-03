package com.example.StudentTeacherCRUD.repository.customImpl;

import com.example.StudentTeacherCRUD.entity.Teacher;
import com.example.StudentTeacherCRUD.repository.custom.CustomTeacherRepository;
import jakarta.persistence.*;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomTeacherRepositoryImpl implements CustomTeacherRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Teacher> findTeacherByName(String name) {
        TypedQuery<Teacher> query = entityManager.createQuery(
                "SELECT a FROM Teacher a WHERE a.name = :name", Teacher.class);
        query.setParameter("name", name);
        List<Teacher> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(resultList.get(0));
        }
    }
}


