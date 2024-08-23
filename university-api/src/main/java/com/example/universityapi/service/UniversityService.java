package com.example.universityapi.service;

import com.example.universityapi.entity.University;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
@Service
public interface UniversityService {
    //Api 1
    List<University> getAllUniversities();
    //Api 2
    CompletableFuture<List<University>> getUniversitiesByCountry(String country);

}
