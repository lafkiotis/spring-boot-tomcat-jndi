package com.example.one.services;

import com.example.one.domain.Actor;
import com.example.one.repositories.sakila.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
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
    @Cacheable("actors")
    public List<Actor> getAll() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return StreamSupport.stream(repository.findAll().spliterator(), true).collect(Collectors.toList());
    }
}
