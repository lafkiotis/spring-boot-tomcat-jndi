package com.example.one.services;

import com.example.one.domain.City;

import java.util.List;

public interface CityService {
    List<City> getAll() throws InterruptedException;

    City getById(long cityId) throws InterruptedException;
}
