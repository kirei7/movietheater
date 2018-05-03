package com.epam.rd.movietheater.model.entity;

import com.epam.rd.movietheater.security.UserRole;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString(exclude = "tickets")
@EqualsAndHashCode(callSuper = true, of = {})
public class User extends IdentifiableEntity {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Ticket> tickets = new HashSet<>();
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @JoinTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "roles", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles = Stream.of(UserRole.REGISTERED_USER).collect(Collectors.toSet());

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

    public void addRole(UserRole role) {
        roles.add(role);
    }
}
