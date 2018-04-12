package com.epam.rd.movietheater.service.booking.pricemodifier;

import com.epam.rd.movietheater.model.entity.Event;

import java.math.BigDecimal;

public interface TicketPriceModifier {
    BigDecimal applyModificationTo(BigDecimal price, Event event, Long seat);
}
