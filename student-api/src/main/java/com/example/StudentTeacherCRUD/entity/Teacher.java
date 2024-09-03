package com.example.StudentTeacherCRUD.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "teacherId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentTeacher> studentTeachers = new HashSet<>();



}
