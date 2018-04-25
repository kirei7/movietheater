package com.epam.rd.movietheater.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@ToString
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"event", "seat"}, callSuper = false)
public class Ticket extends IdentifiableEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    private Long seat;
    private BigDecimal basePrice;
    private int discount;

    public Ticket(Event event, User user, long seat) {
        this.event = event;
        this.user = user;
        this.seat = seat;
    }
}
