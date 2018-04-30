package com.example.one.repositories.sakila;

import com.example.one.domain.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository <Actor, Long> {
}
