package com.epam.rd.movietheater.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
@ToString
public class Auditorium {
    @Getter @Setter private String name;
    @Getter @Setter private long numberOfSeats;
    @Getter @Setter private Set<Long> vipSeats = new HashSet<>();
}
