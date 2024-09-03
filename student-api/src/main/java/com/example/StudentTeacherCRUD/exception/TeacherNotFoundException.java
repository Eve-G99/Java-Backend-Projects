package com.example.StudentTeacherCRUD.exception;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(String s) {
        super("No teacher found with: " + s);
    }

    public TeacherNotFoundException(Long id) {
        super("No teacher found with: " + id);

    }
}
