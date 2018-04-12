package com.epam.rd.movietheater.service.event;

import com.epam.rd.movietheater.dao.EventDao;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.service.AbstractUniqueEntityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class EventServiceImpl extends AbstractUniqueEntityService<Event, EventDao> implements EventService {

    public EventServiceImpl(EventDao dao) {
        super(dao);
    }

    @Override
    public Optional<Event> getByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public List<Event> getForDateRange(LocalDateTime from, LocalDateTime to) {
        if (from.compareTo(to) >= 0)
            throw new IllegalArgumentException("Invalid datetime range : [" + from + ";" + to + "]");
        return dao.findAll().stream().filter(
                event -> isInRange(from, to, event.getAirDate())
        ).collect(toList());
    }

    @Override
    public List<Event> getNextEvents(LocalDateTime to) {
        return getForDateRange(LocalDateTime.now(), to);
    }

    private boolean isInRange(LocalDateTime from, LocalDateTime to, LocalDateTime target) {
        if (target.compareTo(from) >= 1 && target.compareTo(to) <= -1)
            return true;
        return false;
    }
}
