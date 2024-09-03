package com.example.StudentTeacherCRUD.repository;

import com.example.StudentTeacherCRUD.entity.Student;
import com.example.StudentTeacherCRUD.repository.custom.CustomStudentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, CustomStudentRepository {

}
