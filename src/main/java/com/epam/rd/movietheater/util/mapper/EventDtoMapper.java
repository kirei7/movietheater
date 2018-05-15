package com.epam.rd.movietheater.util.mapper;

import com.epam.rd.movietheater.model.dto.EventDto;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.service.auditorium.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventDtoMapper implements EntityMapper<Event, EventDto> {

    private AuditoriumService auditoriumService;

    @Autowired
    public EventDtoMapper(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    @Override
    public Event toEntity(EventDto dto) {
        Event entity = new Event();
        entity.setName(dto.getName());
        entity.setAirDate(dto.getAirDate());
        entity.setBasePrice(dto.getBasePrice());
        entity.setRating(Event.Rating.valueOf(dto.getRating()));
        entity.setAuditorium(auditoriumService.getByName(dto.getAuditoriumName()));
        return entity;
    }

    @Override
    public EventDto toDto(Event event) {
        EventDto dto = new EventDto();
        dto.setName(dto.getName());
        dto.setAirDate(dto.getAirDate());
        dto.setBasePrice(dto.getBasePrice());
        dto.setRating(event.getRating().toString());
        dto.setAuditoriumName(event.getAuditorium().getName());
        return dto;
    }

}
