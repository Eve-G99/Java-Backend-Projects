package com.example.universityapi.service.implement;

import com.example.universityapi.entity.University;
import com.example.universityapi.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UniversityServiceImplement implements UniversityService {

    public UniversityServiceImplement(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    String baseUrl = "http://universities.hipolabs.com/search";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<University> getAllUniversities() {
        ResponseEntity<University[]> response = restTemplate.exchange(
                "http://universities.hipolabs.com/search",
                HttpMethod.GET,
                null,
                University[].class
        );

        University[] universities = response.getBody();
        return mapToFilteredUniversities(universities);
    }

    @Override
    public CompletableFuture<List<University>> getUniversitiesByCountry(String country) {
        return CompletableFuture.supplyAsync(() -> {
            StringBuilder urlBuilder = new StringBuilder(baseUrl);
            urlBuilder.append("?country=").append(country);

            ResponseEntity<University[]> response = restTemplate.exchange(
                    urlBuilder.toString(),
                    HttpMethod.GET,
                    null,
                    University[].class,
                    country
            );

            University[] universities = response.getBody();
            return mapToFilteredUniversities(universities);
        });
    }

    //subtract name, domains and web_pages and map to list
    private List<University> mapToFilteredUniversities(University[] universities) {
        return Stream.of(universities)
                .map(university -> {
                    University filteredUniversity = new University();
                    filteredUniversity.setName(university.getName());
                    filteredUniversity.setDomains(university.getDomains());
                    filteredUniversity.setWeb_pages(university.getWeb_pages());
                    return filteredUniversity;
                })
                .collect(Collectors.toList());
    }
}
