package com.epam.rd.movietheater.model.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventDto implements Dto {
    private String name;
    private LocalDateTime airDate;
    private double basePrice;
    private String rating;
    private String auditoriumName;
}
