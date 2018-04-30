package com.example.one.services;

import com.example.one.domain.City;
import com.example.one.repositories.matrix.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository repository;

    @Autowired
    public CityServiceImpl(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<City> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), true).collect(Collectors.toList());
    }
}
