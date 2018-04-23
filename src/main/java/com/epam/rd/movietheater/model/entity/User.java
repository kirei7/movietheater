package com.epam.rd.movietheater.model.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString(exclude = {"tickets"})
@EqualsAndHashCode(callSuper = true, of = {"firstName", "lastName", "email", "birthday"})
public class User extends IdentifiableEntity {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Ticket> tickets = new HashSet<>();

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
