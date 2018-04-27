package com.epam.rd.movietheater.dao.aspect;

import com.epam.rd.movietheater.model.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("ticketCounter")
public class TicketsBookedCounterDao extends AbstractAspectCounterDao<Event> {

    @Autowired
    public TicketsBookedCounterDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, "tickets_bought");
    }
}
