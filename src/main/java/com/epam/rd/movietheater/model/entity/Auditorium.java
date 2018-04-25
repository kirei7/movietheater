package com.epam.rd.movietheater.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;
@Entity
@ToString
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"name"}, callSuper = false)
public class Auditorium extends IdentifiableEntity {
    private String name;
    private long numberOfSeats;
    @Column
    @ElementCollection(targetClass=Long.class)
    private Set<Long> vipSeats = new HashSet<>();

    public Auditorium(String name, long numberOfSeats) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
    }
}
