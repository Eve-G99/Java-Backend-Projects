package com.example.search.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SearchService {
    List<String> getBooks();
    List<String> getUniversityDetails();
    String fallbackBooks();
    String fallbackUniversityDetails();
}
