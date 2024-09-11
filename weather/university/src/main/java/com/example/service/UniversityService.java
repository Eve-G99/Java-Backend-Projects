package com.example.service;

import com.example.enity.University;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UniversityService {

    University[] getAll();
    List<University> getByCountries(List<String> countries);
}