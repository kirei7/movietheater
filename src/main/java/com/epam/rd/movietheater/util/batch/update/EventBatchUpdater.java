package com.epam.rd.movietheater.util.batch.update;

import com.epam.rd.movietheater.model.dto.EventDto;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.service.event.EventService;
import com.epam.rd.movietheater.util.mapper.EventDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventBatchUpdater implements BatchUpdater<Event, EventDto> {

    private EventService eventService;
    private EventDtoMapper dtoMapper;

    @Autowired
    public EventBatchUpdater(EventService eventService, EventDtoMapper dtoMapper) {
        this.eventService = eventService;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<Event> updateWithData(List<EventDto> data) {
        List<Event> result = new ArrayList<>();
        data.forEach(eventDto -> {
            Event event = dtoMapper.toEntity(eventDto);
            eventService.save(event);
            result.add(event);
        });
        return result;
    }
}
