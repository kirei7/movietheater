package com.epam.rd.movietheater.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@ToString
@Getter @Setter
public class Ticket extends IdentifiableEntity {
    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;
    private Event event;
    private Long seat;
    private BigDecimal basePrice;
    private int discount;
}
