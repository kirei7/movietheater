package com.epam.rd.movietheater.model.dto;


import java.time.LocalDateTime;

public class EventDto implements Dto {
    private String name;
    private LocalDateTime airDate;
    private double basePrice;
    private String rating;
    private String auditoriumName;
}
