package com.epam.rd.movietheater.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public class Auditorium {
    @Getter @Setter private String name;
    @Getter @Setter private long numberOfSeats;
    @Getter @Setter private Set<Long> vipSeats = new HashSet<>();

    public boolean addVipSeat(Long seat) {
        return vipSeats.add(seat);
    }
}
