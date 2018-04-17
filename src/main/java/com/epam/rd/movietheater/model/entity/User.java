package com.epam.rd.movietheater.model.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ToString(exclude = {"tickets"})
@NoArgsConstructor
public class User extends IdentifiableEntity {
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter private String email;
    @Getter @Setter private LocalDate birthday;
    @Getter @Setter private List<Ticket> tickets = new ArrayList<>();

    public User(User other) {
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.email = other.email;
        this.birthday = other.birthday;
        this.tickets = other.tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}