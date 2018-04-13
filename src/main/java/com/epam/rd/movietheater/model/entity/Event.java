package com.epam.rd.movietheater.model.entity;

import com.epam.rd.movietheater.model.Auditorium;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@ToString
public class Event extends UniqueEntity {
    @Getter @Setter private String name;
    @Getter @Setter private LocalDateTime airDate;
    @Getter @Setter private double basePrice;
    @Getter @Setter private Rating rating;
    @Getter @Setter private Auditorium auditorium;
    @Getter @Setter private Set<Long> reservedSeats = new HashSet<>();

    public enum Rating {
        LOW, MID, HIGH
    }

    public void addReservedSeat(Long seat) {
        reservedSeats.add(seat);
    }
}
