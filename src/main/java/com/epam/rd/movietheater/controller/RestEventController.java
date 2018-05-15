package com.epam.rd.movietheater.controller;

import com.epam.rd.movietheater.model.dto.EventDto;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.service.event.EventService;
import com.epam.rd.movietheater.util.mapper.EventDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class RestEventController {

    private EventService eventService;
    private EventDtoMapper eventDtoMapper;

    @Autowired
    public RestEventController(EventService eventService, EventDtoMapper eventDtoMapper) {
        this.eventService = eventService;
        this.eventDtoMapper = eventDtoMapper;
    }

    @DeleteMapping("/{eventId}")
    public EventDto deleteEvent(@PathVariable Long eventId) {
        Event event = eventService.getById(eventId).get();
        eventService.remove(event);
        return eventDtoMapper.toDto(event);
    }
}
