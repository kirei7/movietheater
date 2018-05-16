package com.epam.rd.movietheater.util.json;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class StringToLocalDateConverter extends StdDeserializer<LocalDate> {
    private DateTimeFormatter dateTimeFormatter;

    public StringToLocalDateConverter(@Value("#{DateFormatterProvider.getDateFormatter()}") DateTimeFormatter dateTimeFormatter) {
        super(LocalDateTime.class);
        this.dateTimeFormatter = dateTimeFormatter;
    }
}
