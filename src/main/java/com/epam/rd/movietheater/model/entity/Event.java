package com.epam.rd.movietheater.model.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"name", "airDate", "auditorium"}, callSuper = false)
public class Event extends IdentifiableEntity {
    private String name;
    private LocalDateTime airDate;
    private double basePrice;
    private Rating rating;
    private Auditorium auditorium;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
    private Set<Ticket> reservedTickets = new HashSet<>();

    public Event(String name, LocalDateTime airDate, double basePrice, Event.Rating rating, Auditorium auditorium) {
        this.name = name;
        this.airDate = airDate;
        this.basePrice = basePrice;
        this.rating = rating;
        this.auditorium = auditorium;
    }

    public void addReservedTicket(Ticket ticket) {
        reservedTickets.add(ticket);
    }

    public enum Rating {
        LOW, MID, HIGH
    }


}
