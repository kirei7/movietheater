package com.epam.rd.movietheater.model;

import com.epam.rd.movietheater.model.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Order {
    private List<Ticket> bookedTickets;
    private String sum;
}
