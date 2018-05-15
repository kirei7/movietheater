package com.epam.rd.movietheater.util.json;

import com.epam.rd.movietheater.model.entity.Ticket;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TicketListSerializer extends JsonSerializer<List<Ticket>> {
    @Override
    public void serialize(List<Ticket> tickets, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(tickets.stream().map(Ticket::getSeat).collect(Collectors.toList()).toString());
    }
}