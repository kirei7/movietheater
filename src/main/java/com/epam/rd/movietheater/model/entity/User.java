package com.epam.rd.movietheater.model.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString(exclude = {"tickets"})
@EqualsAndHashCode(callSuper = true, of = {})
public class User extends IdentifiableEntity {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Ticket> tickets = new HashSet<>();

    public User(String firstName, String lastName, String email, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
    }

    public User(User other) {
        this.id = other.id;
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.email = other.email;
        this.birthday = other.birthday;
        this.tickets = new HashSet<>(other.tickets);
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}
