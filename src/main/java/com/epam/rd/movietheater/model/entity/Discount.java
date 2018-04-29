package com.epam.rd.movietheater.model.entity;

import com.epam.rd.movietheater.service.discount.strategy.DiscountStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Discount implements Serializable {
    @Id
    @Column(name =  "discount_id")
    private Long id;

    @MapsId
    @OneToOne(mappedBy = "discount")
    @JoinColumn(name = "discount_id")
    private Ticket ticket;
    private String type;
    private int amount;

    public Discount(String type, Ticket ticket, int amount) {
        this.type = type;
        this.ticket = ticket;
        this.amount = amount;
    }

}
