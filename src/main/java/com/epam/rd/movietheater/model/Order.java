package com.epam.rd.movietheater.model;

import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.util.json.TicketListSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Order implements Serializable {
    @JsonSerialize(using = TicketListSerializer.class)
    private List<Ticket> bookedTickets;
    private String sum;
}
