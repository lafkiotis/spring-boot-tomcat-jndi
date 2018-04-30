package com.example.one.services;

import com.example.one.domain.Actor;
import com.example.one.repositories.sakila.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository repository;

    @Autowired
    public ActorServiceImpl(ActorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Actor> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), true).collect(Collectors.toList());
    }
}
