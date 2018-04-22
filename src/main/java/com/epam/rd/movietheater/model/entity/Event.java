package com.epam.rd.movietheater.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
@Getter @Setter
public class Event extends IdentifiableEntity {
    private String name;
    private LocalDateTime airDate;
    private double basePrice;
    private Rating rating;
    private Auditorium auditorium;
    @Column
    @ElementCollection(targetClass=Long.class)
    private Set<Long> reservedSeats = new HashSet<>();

    public enum Rating {
        LOW, MID, HIGH
    }

    public void addReservedSeat(Long seat) {
        reservedSeats.add(seat);
    }
}
