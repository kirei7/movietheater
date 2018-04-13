package com.epam.rd.movietheater.model.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.NavigableSet;
import java.util.TreeSet;

@ToString
@NoArgsConstructor
public class User extends UniqueEntity{
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter private String email;
    @Getter @Setter private NavigableSet<Ticket> tickets = new TreeSet<>();
    @Getter @Setter private LocalDate birthDay;
    @Getter @Setter private long ticketsBought;

    public User(User other) {
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.email = other.email;
        this.tickets = other.tickets;
        this.birthDay = other.birthDay;
        this.ticketsBought = other.ticketsBought;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}
