package com.example.one.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "city")
@Getter
@Setter
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long cityId;

    @Column(name = "name")
    private String name;
}
