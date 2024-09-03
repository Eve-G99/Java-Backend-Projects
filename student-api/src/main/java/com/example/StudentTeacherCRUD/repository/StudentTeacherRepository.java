package com.example.StudentTeacherCRUD.repository;

import com.example.StudentTeacherCRUD.entity.StudentTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentTeacherRepository extends JpaRepository<StudentTeacher, Long> {
    void deleteByStudentId(Long studentId);
    void deleteByTeacherId(Long teacherId);
    List<StudentTeacher> findByStudentId(Long studentId);
}
