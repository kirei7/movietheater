package com.epam.rd.movietheater.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class Ticket extends UniqueEntity{
    @Getter @Setter private User user;
    @Getter @Setter private Event event;
    @Getter @Setter private LocalDateTime dateTime;
    @Getter @Setter private Long seat;

}
