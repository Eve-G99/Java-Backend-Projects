package com.example.StudentTeacherCRUD.service.impl;

import com.example.StudentTeacherCRUD.dto.StudentDTO;
import com.example.StudentTeacherCRUD.entity.Teacher;
import com.example.StudentTeacherCRUD.entity.Student;
import com.example.StudentTeacherCRUD.entity.StudentTeacher;
import com.example.StudentTeacherCRUD.exception.StudentNotFoundException;
import com.example.StudentTeacherCRUD.repository.TeacherRepository;
import com.example.StudentTeacherCRUD.repository.StudentTeacherRepository;
import com.example.StudentTeacherCRUD.repository.StudentRepository;
import com.example.StudentTeacherCRUD.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentTeacherRepository studentTeacherRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, TeacherRepository teacherRepository, StudentTeacherRepository studentTeacherRepository){
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.studentTeacherRepository = studentTeacherRepository;
    }


    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        studentRepository.save(student);

        for (String teacherName : studentDTO.getTeacherNames()) {
            Teacher teacher = teacherRepository.findTeacherByName(teacherName)
                    .orElseGet(() -> {
                        Teacher newTeacher = new Teacher();
                        newTeacher.setName(teacherName);
                        return teacherRepository.save(newTeacher);
                    });

            StudentTeacher studentTeacher = new StudentTeacher();
            studentTeacher.setStudentId(student.getId());
            studentTeacher.setTeacherId(teacher.getId());
            studentTeacherRepository.save(studentTeacher);
        }

        return convertToDTO(student, studentDTO.getTeacherNames());
    }



    @Transactional
    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        existingStudent.setName(studentDTO.getName());
        studentRepository.save(existingStudent);

        studentTeacherRepository.deleteByStudentId(existingStudent.getId());

        for (String teacherName : studentDTO.getTeacherNames()) {
            Teacher teacher = teacherRepository.findTeacherByName(teacherName)
                    .orElseGet(() -> {
                        Teacher newTeacher = new Teacher();
                        newTeacher.setName(teacherName);
                        return teacherRepository.save(newTeacher);
                    });

            StudentTeacher studentTeacher = new StudentTeacher();
            studentTeacher.setStudentId(existingStudent.getId());
            studentTeacher.setTeacherId(teacher.getId());
            studentTeacherRepository.save(studentTeacher);
        }

        return convertToDTO(existingStudent, studentDTO.getTeacherNames());
    }


    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> {
                    List<String> teacherNames = studentTeacherRepository.findByStudentId(student.getId())
                            .stream()
                            .map(studentTeacher -> teacherRepository.findById(studentTeacher.getTeacherId()).orElseThrow().getName())
                            .collect(Collectors.toList());
                    return convertToDTO(student, teacherNames);
                })
                .collect(Collectors.toList());
    }

    // Retrieve a student by its ID
    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

        List<String> teacherNames = studentTeacherRepository.findByStudentId(student.getId())
                .stream()
                .map(studentTeacher -> teacherRepository.findById(studentTeacher.getTeacherId()).orElseThrow().getName())
                .collect(Collectors.toList());

        return convertToDTO(student, teacherNames);
    }


    // Get Students by name
    @Override
    public List<StudentDTO> getStudentsByName(String name) {
        List<Student> students = studentRepository.findStudentsByName(name);

        // Check if the list is empty and throw exception
        if (students.isEmpty()) {
            throw new StudentNotFoundException(name);
        }

        return students.stream()
                .map(student -> {
                    List<String> teacherNames = studentTeacherRepository.findByStudentId(student.getId())
                            .stream()
                            .map(studentTeacher -> teacherRepository.findById(studentTeacher.getTeacherId()).orElseThrow().getName())
                            .collect(Collectors.toList());
                    return convertToDTO(student, teacherNames);
                })
                .collect(Collectors.toList());
    }

    public List<StudentDTO> getStudentsByTeacherName(String teacherName) {
        List<Student> students = studentRepository.findStudentsByTeacherName(teacherName);

        // Check if the list is empty and return empty list
        if (students.isEmpty()) {
            throw new StudentNotFoundException(teacherName);
        }
        return students.stream()
                .map(student -> {
                    List<String> teacherNames = studentTeacherRepository.findByStudentId(student.getId())
                            .stream()
                            .map(studentTeacher -> teacherRepository.findById(studentTeacher.getTeacherId()).orElseThrow().getName())
                            .collect(Collectors.toList());
                    return convertToDTO(student, teacherNames);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        studentTeacherRepository.deleteByStudentId(student.getId());
        studentRepository.deleteById(studentId);
    }

    @Transactional
    @Override
    public List<StudentDTO> findStudentsWithTeachersWhereStudentIdGreaterThanTen() {
        List<Student> students = studentRepository.findStudentsWithTeachersWhereStudentIdGreaterThanTen();
        if (students.isEmpty()) {
            throw new StudentNotFoundException("s_id > 10");
        }
        return students.stream()
                .map(student -> {
                    List<String> teacherNames = studentTeacherRepository.findByStudentId(student.getId())
                            .stream()
                            .map(studentTeacher -> teacherRepository.findById(studentTeacher.getTeacherId()).orElseThrow().getName())
                            .collect(Collectors.toList());
                    return convertToDTO(student, teacherNames);
                })
                .collect(Collectors.toList());
    }

    private StudentDTO convertToDTO(Student student, List<String> teacherNames) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setTeacherNames(teacherNames);
        return studentDTO;
    }
}
