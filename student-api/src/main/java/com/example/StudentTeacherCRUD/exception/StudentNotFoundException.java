package com.example.StudentTeacherCRUD.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String s) {
        super("No student found with: " + s);
    }

    public StudentNotFoundException(Long id) {
        super("No student found with ID: " + id);
    }
}