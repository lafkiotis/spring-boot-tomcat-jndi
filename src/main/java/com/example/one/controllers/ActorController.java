package com.example.one.controllers;

import com.example.one.domain.Actor;
import com.example.one.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("actors")
    public ResponseEntity<List<Actor>> getAllActors() {
        List<Actor> list = actorService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
