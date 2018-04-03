package com.epam.rd.movietheater.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Ticket extends UniqueEntity{
    @Getter @Setter private User user;
    @Getter @Setter private Event event;
    @Getter @Setter private LocalDateTime dateTime;
    @Getter @Setter private Long seat;

}
