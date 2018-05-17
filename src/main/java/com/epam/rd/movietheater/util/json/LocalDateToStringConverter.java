package com.epam.rd.movietheater.util.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class LocalDateToStringConverter extends JsonSerializer<LocalDate> {

    private DateTimeFormatter dateFormatter;

    public LocalDateToStringConverter(@Value("#{DateFormatterProvider.getDateFormatter()}") DateTimeFormatter dateTimeFormatter) {
        this.dateFormatter = dateTimeFormatter;
    }

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(localDate.format(dateFormatter));
    }
}
