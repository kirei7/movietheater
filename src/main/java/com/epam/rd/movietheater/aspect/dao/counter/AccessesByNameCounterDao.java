package com.epam.rd.movietheater.aspect.dao.counter;

import com.epam.rd.movietheater.model.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("nameCounter")
public class AccessesByNameCounterDao extends AbstractAspectCounterDao<Event> {

    @Autowired
    public AccessesByNameCounterDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, "accessed_name");
    }

}
