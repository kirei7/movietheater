package com.epam.rd.movietheater.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class UserAccount extends IdentifiableEntity {
    @MapsId
    @OneToOne(cascade = CascadeType.MERGE)
    private User owner;
    /**
     * Amount of money on account in mills (1/1000 of unit)
     */
    private Long amount = 0L;
}
