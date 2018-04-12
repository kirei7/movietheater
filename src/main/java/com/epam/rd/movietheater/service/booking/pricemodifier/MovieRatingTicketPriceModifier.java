package com.epam.rd.movietheater.service.booking.pricemodifier;

import com.epam.rd.movietheater.model.entity.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MovieRatingTicketPriceModifier implements TicketPriceModifier {

    @Value("${pricemodifier.movierating}")
    private float modifier;

    @Override
    public BigDecimal applyModificationTo(BigDecimal price, Event event, Long seat) {
        float resultingModifier = event.getRating().equals(Event.Rating.HIGH) ? modifier : 1;
        return price.multiply(new BigDecimal(resultingModifier));
    }
}
