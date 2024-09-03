package com.example.StudentTeacherCRUD.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeacherDTO {
    private Long id;
    private String name;
    private List<String> studentNames;
}
