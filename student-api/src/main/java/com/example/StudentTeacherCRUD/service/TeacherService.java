package com.example.StudentTeacherCRUD.service;

import com.example.StudentTeacherCRUD.dto.TeacherDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {

    public TeacherDTO createTeacher(TeacherDTO teacherDTO);
    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO);
    public List<TeacherDTO> getAllTeachers();
    public TeacherDTO getTeacher(Long teacherId);
    public TeacherDTO findTeacherByName(String name);
    public void deleteTeacher(Long teacherId);
}
