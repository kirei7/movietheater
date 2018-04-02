package com.epam.rd.movietheater.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.NavigableSet;
import java.util.TreeSet;

public class User {
    @Getter @Setter private Long id;
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter private String email;
    @Getter @Setter private NavigableSet<Ticket> tickets = new TreeSet<>();

}
