package com.epam.rd.movietheater.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
public class Ticket extends IdentifiableEntity {
    @Getter @Setter private User user;
    @Getter @Setter private Event event;
    @Getter @Setter private Long seat;
    @Getter @Setter private BigDecimal basePrice;
    @Getter @Setter private int discount;


}
