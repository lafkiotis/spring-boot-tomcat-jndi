package com.example.one.controllers;

import com.example.one.domain.City;
import com.example.one.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("jis/secure/")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("cities")
    public ResponseEntity<List<City>> getAllCities() {
        List<City> list = cityService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
