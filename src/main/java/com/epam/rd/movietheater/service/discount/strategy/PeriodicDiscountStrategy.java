package com.epam.rd.movietheater.service.discount.strategy;

import com.epam.rd.movietheater.model.entity.Discount;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PeriodicDiscountStrategy extends AbstractDisountStrategy implements DiscountStrategy {

    @Value("${discount.strategy.step}")
    private int step;
    @Value("${discount.amount.periodic}")
    private int discountAmount;

    @Override
    public List<Ticket> calculateDiscount(List<Ticket> tickets) {
        User user = tickets.get(0).getUser();
        int alreadyBought = user.getTickets().size() + 1;
        for (int i = 0; i < tickets.size(); i++) {
            calculateForTicket(tickets.get(i), alreadyBought + i);
        }
        return tickets;
    }
    private void calculateForTicket(Ticket ticket, int ticketNumber) {
        if (ticketNumber % 10 == 0 && ticketNumber > 0) {
            ticket.setDiscount(
                    getMaxDiscount(ticket.getDiscount(), new Discount(getClass().getSimpleName(), ticket, discountAmount))
            );
        }
    }
}
