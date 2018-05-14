package com.epam.rd.movietheater.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@ToString(exclude = "reservedTickets")
@Getter @Setter
@NoArgsConstructor
public class Event extends IdentifiableEntity {
    private String name;
    private LocalDateTime airDate;
    private double basePrice;
    @Enumerated(EnumType.STRING)
    private Rating rating;
    @ManyToOne
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;
    @OneToMany(mappedBy = "event", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Event event = (Event) o;
        return Objects.equals(name, event.name) &&
                compareDates(airDate, event.airDate) &&
                Objects.equals(auditorium, event.auditorium);
    }
    private boolean compareDates(LocalDateTime firstDate, LocalDateTime secondDate) {
        if (firstDate != null && secondDate != null) {
            return trimDate(firstDate).equals(trimDate(secondDate));
        }
        else {
            return Objects.equals(firstDate, secondDate);
        }
    }
    private LocalDateTime trimDate(LocalDateTime date) {
        return date.withSecond(0).withNano(0);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, trimDate(airDate), auditorium);
    }
}
