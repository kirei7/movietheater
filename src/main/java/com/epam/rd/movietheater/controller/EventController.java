package com.epam.rd.movietheater.controller;

import com.epam.rd.movietheater.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/events")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping
    public String getAll(Model model) {
        model.addAttribute("events", eventService.getAll());
        return "events";
    }
}
