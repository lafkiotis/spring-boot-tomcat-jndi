package com.example.one.services;

import com.example.one.domain.City;
import com.example.one.repositories.matrix.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
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
    @Cacheable("cities")
    public List<City> getAll() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return StreamSupport.stream(repository.findAll().spliterator(), true).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "cities.byCityId", key = "#cityId")
    public City getById(long cityId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return repository.findById(cityId).get();
    }
}
