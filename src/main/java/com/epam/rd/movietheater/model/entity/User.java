package com.epam.rd.movietheater.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@ToString(exclude = {"tickets"})
@NoArgsConstructor
public class User extends IdentifiableEntity {
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter private String email;
    @Getter @Setter private LocalDate birthday;
    @Getter @Setter private Set<Ticket> tickets = new HashSet<>();

    public User(User other) {
        this.id = other.id;
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
