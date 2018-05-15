package com.epam.rd.movietheater.util.json.formatter;

import java.time.format.DateTimeFormatter;

public interface DateFormatterProvider {
    DateTimeFormatter getFormatter();
}
