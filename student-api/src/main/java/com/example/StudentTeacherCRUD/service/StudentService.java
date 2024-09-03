package com.example.StudentTeacherCRUD.service;

import com.example.StudentTeacherCRUD.dto.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {


    public StudentDTO createStudent(StudentDTO studentDTO);
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO);
    public List<StudentDTO> getAllStudents();
    public StudentDTO getStudentById(Long id);
    public List<StudentDTO> getStudentsByName(String title);
    public List<StudentDTO> getStudentsByTeacherName(String teacherName);
    public void deleteStudent(Long studentId);

    public List<StudentDTO> findStudentsWithTeachersWhereStudentIdGreaterThanTen();
}
