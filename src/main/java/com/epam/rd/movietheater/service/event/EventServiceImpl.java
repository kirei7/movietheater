package com.epam.rd.movietheater.service.event;

import com.epam.rd.movietheater.dao.EventDao;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.service.AbstractIdentifiableService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class EventServiceImpl extends AbstractIdentifiableService<Event, EventDao> implements EventService {

    public EventServiceImpl(EventDao dao) {
        super(dao);
    }

    @Override
    public List<Event> getByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public List<Event> getForDateRange(LocalDate from, LocalDate to) {
        if (from.compareTo(to) >= 0)
            throw new IllegalArgumentException("Invalid datetime range : [" + from + ";" + to + "]");
        return findForRange(from, to);
    }
    private List<Event> findForRange(LocalDate from, LocalDate to) {
        return dao.findAll().stream().filter(
                event -> isInRange(from, to, event.getAirDate().toLocalDate())
        ).collect(toList());
    }
    @Override
    public List<Event> getNextEvents(LocalDate to) {
        return getForDateRange(LocalDate.now(), to);
    }

    private boolean isInRange(LocalDate from, LocalDate to, LocalDate target) {
        return target.compareTo(from) >= 0 && target.compareTo(to) < 0;
    }
}
