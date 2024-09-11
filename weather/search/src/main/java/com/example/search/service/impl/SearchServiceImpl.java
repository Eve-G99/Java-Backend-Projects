package com.example.search.service.impl;

import com.example.search.service.SearchService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
@Slf4j
public class SearchServiceImpl implements SearchService {

    private final RestTemplate restTemplate;
    private final ExecutorService executorService;

    @Autowired
    public SearchServiceImpl(RestTemplate restTemplate, ExecutorService executorService) {
        this.restTemplate = restTemplate;
        this.executorService = executorService;
    }


    @Override
    @HystrixCommand(fallbackMethod = "fallbackBooks")
    public List<String> getBooks() {
        Callable<String> task = () -> restTemplate.getForObject("http://library/books", String.class);
        Future<String> future = executorService.submit(task);
        try {
            return Arrays.asList(future.get());
        } catch (Exception e) {
            return Arrays.asList(fallbackBooks());
        }
    }


    @Override
    @HystrixCommand(fallbackMethod = "fallbackUniversityDetails")
    public List<String> getUniversityDetails() {
        Callable<String> task = () -> restTemplate.getForObject("http://localhost:8500/university", String.class);
        Future<String> future = executorService.submit(task);
        try {
            return Arrays.asList(future.get());
        } catch (Exception e) {
            return Arrays.asList(fallbackUniversityDetails());
        }
    }

    @Override
    public String fallbackBooks() {
        return "Fallback books";
    }


    @Override
    public String fallbackUniversityDetails() {
        return "Fallback university details";
    }
}
