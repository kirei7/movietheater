package com.epam.rd.movietheater.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@ToString(exclude = "events")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"name"}, callSuper = false)
public class Auditorium extends IdentifiableEntity {
    private String name;
    private long numberOfSeats;
    @Column
    @ElementCollection(targetClass = Long.class, fetch = FetchType.EAGER)
    private Set<Long> vipSeats = new HashSet<>();
    @OneToMany(mappedBy = "auditorium", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Event> events = new ArrayList<>();

    public Auditorium(String name, long numberOfSeats) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
    }
}
