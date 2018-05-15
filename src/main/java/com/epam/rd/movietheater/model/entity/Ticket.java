package com.epam.rd.movietheater.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@ToString
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"seat"}, callSuper = false)
public class Ticket extends IdentifiableEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Event event;
    private Long seat;
    /**
     * Price without discount applied
     */
    private BigDecimal price;
    @OneToOne(cascade = CascadeType.ALL)
    private Discount discount = new Discount();

    public Ticket(Event event, User user, long seat) {
        this.event = event;
        this.user = user;
        this.seat = seat;
    }
}
