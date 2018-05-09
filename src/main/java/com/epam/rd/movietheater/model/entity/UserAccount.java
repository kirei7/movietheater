package com.epam.rd.movietheater.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class UserAccount extends IdentifiableEntity {
    @MapsId
    @OneToOne(cascade = CascadeType.MERGE)
    private User user;
    /**
     * Amount of money on account in mills (1/1000 of unit)
     */
    private BigDecimal amount = BigDecimal.ZERO;
}
