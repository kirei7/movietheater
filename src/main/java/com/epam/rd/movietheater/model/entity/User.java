package com.epam.rd.movietheater.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.NavigableSet;
import java.util.TreeSet;

@ToString
public class User extends UniqueEntity{
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter private String email;
    @Getter @Setter private NavigableSet<Ticket> tickets = new TreeSet<>();
    @Getter @Setter private LocalDate birthDay;
    @Getter @Setter private long ticketsBought;

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}
