package com.epam.rd.movietheater.service.discount;

import com.epam.rd.movietheater.model.entity.Ticket;
import java.util.List;

public interface DiscountService {
    void assignDiscounts(List<Ticket> tickets);
}
