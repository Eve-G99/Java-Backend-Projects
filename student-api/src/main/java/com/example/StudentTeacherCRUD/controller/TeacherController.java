package com.example.StudentTeacherCRUD.controller;

import com.example.StudentTeacherCRUD.dto.TeacherDTO;
import com.example.StudentTeacherCRUD.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacherDTO) {
        TeacherDTO createdTeacher = teacherService.createTeacher(teacherDTO);
        return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO) {
        TeacherDTO updatedTeacher = teacherService.updateTeacher(id, teacherDTO);
        return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        List<TeacherDTO> teachers = teacherService.getAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacher(@PathVariable Long id) {
        TeacherDTO teacher = teacherService.getTeacher(id);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @GetMapping("/byName")
    public ResponseEntity<TeacherDTO> findTeacherByName(@RequestParam String name) {
        TeacherDTO teacher = teacherService.findTeacherByName(name);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return new ResponseEntity<>("Teacher with ID " + id + " has been deleted successfully", HttpStatus.OK);
    }


}
