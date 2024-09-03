package com.example.StudentTeacherCRUD.service.impl;

import com.example.StudentTeacherCRUD.dto.TeacherDTO;
import com.example.StudentTeacherCRUD.entity.Teacher;
import com.example.StudentTeacherCRUD.exception.TeacherNotFoundException;
import com.example.StudentTeacherCRUD.repository.TeacherRepository;
import com.example.StudentTeacherCRUD.repository.StudentTeacherRepository;
import com.example.StudentTeacherCRUD.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final StudentTeacherRepository studentTeacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, StudentTeacherRepository studentTeacherRepository){
        this.teacherRepository = teacherRepository;
        this.studentTeacherRepository = studentTeacherRepository;
    }

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherDTO.getName());
        Teacher savedTeacher = teacherRepository.save(teacher);
        return convertToDTO(savedTeacher);
    }

    @Override
    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) {
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(id));
        existingTeacher.setName(teacherDTO.getName());
        Teacher updatedTeacher = teacherRepository.save(existingTeacher);
        return convertToDTO(updatedTeacher);
    }


    @Override
    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream().map(this::convertToDTO).toList();
    }

    @Override
    public TeacherDTO getTeacher(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException(teacherId));
        return convertToDTO(teacher);
    }

    @Override
    public TeacherDTO findTeacherByName(String name) {
        Teacher teacher = teacherRepository.findTeacherByName(name)
                .orElseThrow(() -> new TeacherNotFoundException(name));
        return convertToDTO(teacher);
    }

    @Override
    public void deleteTeacher(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException(teacherId));
        studentTeacherRepository.deleteByTeacherId(teacher.getId());
        teacherRepository.deleteById(teacherId);
    }

    private TeacherDTO convertToDTO(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setName(teacher.getName());
        return teacherDTO;
    }


}
