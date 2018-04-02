package com.epam.rd.movietheater.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Event {
    @Getter @Setter private Long id;
    @Getter @Setter private String name;
    @Getter @Setter private NavigableSet<LocalDateTime> airDates = new TreeSet<>();
    @Getter @Setter private double basePrice;
    @Getter @Setter private Rating rating;
    @Getter @Setter private NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();

    public static enum Rating {
        LOW, MID, HIGH;
    }

    /**
     * Checks if event is aired on particular dateTime and assigns
     * auditorium to it.
     */
    public boolean assignAuditorium(LocalDateTime dateTime, Auditorium auditorium) {
        if (airDates.contains(dateTime)) {
            auditoriums.put(dateTime, auditorium);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes auditorium assignment from event
     */
    public boolean removeAuditoriumAssignment(LocalDateTime dateTime) {
        return auditoriums.remove(dateTime) != null;
    }

    /**
     * Add date and time of event air
     */
    public boolean addAirDateTime(LocalDateTime dateTime) {
        return airDates.add(dateTime);
    }

    /**
     * Adding date and time of event air and assigning auditorium to that
     */
    public boolean addAirDateTime(LocalDateTime dateTime, Auditorium auditorium) {
        boolean result = airDates.add(dateTime);
        if (result) {
            auditoriums.put(dateTime, auditorium);
        }
        return result;
    }

    /**
     * Removes the date and time of event air. If auditorium was assigned to
     * that date and time - the assignment is also removed
     */
    public boolean removeAirDateTime(LocalDateTime dateTime) {
        boolean result = airDates.remove(dateTime);
        if (result) {
            auditoriums.remove(dateTime);
        }
        return result;
    }

    /**
     * Checks if event airs on particular date and time
     */
    public boolean airsOnDateTime(LocalDateTime dateTime) {
        return airDates.stream().anyMatch(dt -> dt.equals(dateTime));
    }

    /**
     * Checks if event airs on particular date
     */
    public boolean airsOnDate(LocalDate date) {
        return airDates.stream().anyMatch(dt -> dt.toLocalDate().equals(date));
    }

    /**
     * Checking if event airs on dates between from and
     * to inclusive
     */
    public boolean airsOnDates(LocalDate from, LocalDate to) {
        return airDates.stream()
                .anyMatch(dt -> dt.toLocalDate().compareTo(from) >= 0 && dt.toLocalDate().compareTo(to) <= 0);
    }


}
