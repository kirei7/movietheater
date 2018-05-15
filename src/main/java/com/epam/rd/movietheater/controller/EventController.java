package com.epam.rd.movietheater.controller;

import com.epam.rd.movietheater.exception.EventNotFoundException;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.service.booking.BookingService;
import com.epam.rd.movietheater.service.event.EventService;
import com.epam.rd.movietheater.util.pdf.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/events")
public class EventController {

    private EventService eventService;
    private BookingService bookingService;
    private PdfGenerator pdfGenerator;

    @Autowired
    public EventController(EventService eventService, BookingService bookingService, PdfGenerator pdfGenerator) {
        this.eventService = eventService;
        this.bookingService = bookingService;
        this.pdfGenerator = pdfGenerator;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("events", eventService.getAll());
        return "events";
    }

    @GetMapping(value = "/{eventId}")
    public String findOne(@PathVariable Long eventId, Model model) {
        Event event = eventService.getById(eventId).orElseThrow(EventNotFoundException::new);
        model.addAttribute("event", event);
        return "event";
    }


    @GetMapping(value = "/{eventId}/tickets", headers = "Accept=text/html")
    public String getTicketsForEventView(@PathVariable Long eventId, Model model) {
        Event event = eventService.getById(eventId).orElseThrow(EventNotFoundException::new);
        model.addAttribute(
                "event",
                event
        );
        return "bookedForEvent";
    }

    @GetMapping(value = "/{eventId}/tickets/pdf")
    public ResponseEntity<byte[]> getTicketsForEventPdf(@PathVariable Long eventId) {
        Map<String, Object> model = getTicketsForEventModel(eventId);
        byte[] pdfFile = pdfGenerator.generatePdf(model, "bookedForEventPdf");
        return createPdfResponceEntity(pdfFile);
    }


    private Map<String, Object> getTicketsForEventModel(Long eventId) {
        Event event = eventService.getById(eventId).orElseThrow(EventNotFoundException::new);
        Map<String, Object> model = new HashMap<>();
        model.put(
                "event",
                event
        );
        model.put(
                "tickets",
                bookingService.getPurchasedTicketsForEvent(event)
        );
        return model;
    }

    private ResponseEntity<byte[]> createPdfResponceEntity(byte[] pdfFile) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/download"));
        String filename = "tickets.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(pdfFile, headers, HttpStatus.OK);
        return response;
    }
}
