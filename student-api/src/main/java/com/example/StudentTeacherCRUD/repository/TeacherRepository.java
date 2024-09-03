package com.example.StudentTeacherCRUD.repository;

import com.example.StudentTeacherCRUD.entity.Teacher;
import com.example.StudentTeacherCRUD.repository.custom.CustomTeacherRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Optional, but can be added for consistency
public interface TeacherRepository extends JpaRepository<Teacher, Long>, CustomTeacherRepository {

}
