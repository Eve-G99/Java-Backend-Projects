package com.example.StudentTeacherCRUD.controller;

import com.example.StudentTeacherCRUD.dto.StudentDTO;
import com.example.StudentTeacherCRUD.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping()
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(createdStudent, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }



    @GetMapping()
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentsById(@PathVariable Long id) {
        StudentDTO studentDTO = studentService.getStudentById(id);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @GetMapping("/byName")
    public ResponseEntity<List<StudentDTO>> getStudentsByName(@RequestParam String title) {
        List<StudentDTO> students = studentService.getStudentsByName(title);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/byTeacher")
    public ResponseEntity<List<StudentDTO>> getStudentsByTeacherName(@RequestParam String teacherName) {
        List<StudentDTO> students = studentService.getStudentsByTeacherName(teacherName);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Student with ID " + id + " has been deleted successfully", HttpStatus.OK);
    }

    // s_id greater than 10
    @GetMapping("/idgt10")
    public ResponseEntity<List<StudentDTO>> getStudentsWithTeacherNameIDGT10() {
        List<StudentDTO> students = studentService.findStudentsWithTeachersWhereStudentIdGreaterThanTen();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
