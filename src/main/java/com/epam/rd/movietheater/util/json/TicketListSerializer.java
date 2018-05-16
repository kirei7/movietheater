package com.epam.rd.movietheater.util.json;

import com.epam.rd.movietheater.model.entity.Ticket;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TicketListSerializer extends JsonSerializer<List<Ticket>> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void serialize(List<Ticket> tickets, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        List<Ticket> result = new ArrayList<>(tickets);
        result.forEach(ticket -> {
            ticket.setEvent(null);
            ticket.getDiscount().setTicket(null);
            ticket.setUser(null);
        });
        jsonGenerator.writeObject(objectMapper.writeValueAsString(result));
    }
}