package com.example.StudentTeacherCRUD.repository.custom;

import com.example.StudentTeacherCRUD.entity.Teacher;

import java.util.Optional;

public interface CustomTeacherRepository {
    Optional<Teacher> findTeacherByName(String name);


}
