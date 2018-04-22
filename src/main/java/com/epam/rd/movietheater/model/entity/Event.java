package com.epam.rd.movietheater.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
    private Set<Ticket> reservedTickets;

    public enum Rating {
        LOW, MID, HIGH
    }

    public void addReservedTicket(Ticket ticket) {
        reservedTickets.add(ticket);
    }
}
