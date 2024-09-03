package com.example.StudentTeacherCRUD.service.impl;

import com.example.StudentTeacherCRUD.entity.Student;
import com.example.StudentTeacherCRUD.entity.Teacher;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.StudentTeacherCRUD.dto.StudentDTO;
import com.example.StudentTeacherCRUD.entity.StudentTeacher;
import com.example.StudentTeacherCRUD.repository.TeacherRepository;
import com.example.StudentTeacherCRUD.repository.StudentTeacherRepository;
import com.example.StudentTeacherCRUD.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private StudentTeacherRepository studentTeacherRepository;

    @InjectMocks
    private StudentServiceImpl studentServiceImpl;

    @Test
    void testGetStudentById() {
        Long studentId = 1L;
        Student student = new Student();
        student.setId(studentId);
        student.setName("Test Student");

        StudentTeacher studentTeacher = new StudentTeacher();
        studentTeacher.setStudentId(studentId);
        studentTeacher.setTeacherId(1L);

        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("Test Teacher");

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentTeacherRepository.findByStudentId(studentId)).thenReturn(Arrays.asList(studentTeacher));
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));

        StudentDTO result = studentServiceImpl.getStudentById(studentId);

        assertEquals(studentId, result.getId());
        assertEquals("Test Student", result.getName());
        assertEquals(Arrays.asList("Test Teacher"), result.getTeacherNames());
    }


}
