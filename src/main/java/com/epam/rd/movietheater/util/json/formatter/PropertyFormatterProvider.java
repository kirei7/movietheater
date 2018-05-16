package com.epam.rd.movietheater.util.json.formatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component("DateFormatterProvider")
public class PropertyFormatterProvider implements DateFormatterProvider {

    private final String DATE_TIME_PATTERN;
    private final String DATE_PATTERN;

    public PropertyFormatterProvider(@Value("${datetime.pattern}") String date_time_pattern, @Value("${date.pattern}") String date_pattern) {
        DATE_TIME_PATTERN = date_time_pattern;
        DATE_PATTERN = date_pattern;
    }

    @Override
    public DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    }

    @Override
    public DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ofPattern(DATE_PATTERN);
    }
}
