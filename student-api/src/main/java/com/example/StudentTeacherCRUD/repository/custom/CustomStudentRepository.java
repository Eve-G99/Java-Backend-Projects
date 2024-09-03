package com.example.StudentTeacherCRUD.repository.custom;

import com.example.StudentTeacherCRUD.entity.Student;

import java.util.List;

public interface CustomStudentRepository {
    List<Student> findStudentsByName(String name);
    List<Student> findStudentsByTeacherName(String teacherName);
    List<Student> findStudentsWithTeachersWhereStudentIdGreaterThanTen();
}
