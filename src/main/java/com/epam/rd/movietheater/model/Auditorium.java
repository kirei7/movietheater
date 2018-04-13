package com.epam.rd.movietheater.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = {"name"})
public class Auditorium {
    @Getter @Setter private String name;
    @Getter @Setter private long numberOfSeats;
    @Getter @Setter private Set<Long> vipSeats = new HashSet<>();

    public Auditorium(String name, long numberOfSeats) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
    }
}
