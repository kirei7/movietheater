package com.epam.rd.movietheater.util.json.formatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component("DateFormatterProvider")
public class PropertyFormatterProvider implements DateFormatterProvider {

    private final String DATE_TIME_PATTERN;

    public PropertyFormatterProvider(@Value("${datetime.pattern}") String date_time_pattern) {
        DATE_TIME_PATTERN = date_time_pattern;
    }

    @Override
    public DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    }
}
