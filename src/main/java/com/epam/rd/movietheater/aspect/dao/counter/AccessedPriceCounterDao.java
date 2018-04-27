package com.epam.rd.movietheater.aspect.dao.counter;

import com.epam.rd.movietheater.model.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("priceCounter")
public class AccessedPriceCounterDao extends AbstractAspectCounterDao<Event> {

    @Autowired
    public AccessedPriceCounterDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, "accessed_price");
    }

}
