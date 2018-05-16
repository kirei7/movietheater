package com.epam.rd.movietheater.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class StringToLocalDateConverter extends StdDeserializer<LocalDate> {

    private DateTimeFormatter dateFormatter;

    public StringToLocalDateConverter(@Value("#{DateFormatterProvider.getDateFormatter()}") DateTimeFormatter dateTimeFormatter) {
        super(LocalDateTime.class);
        this.dateFormatter = dateTimeFormatter;
    }

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String content = p.readValueAs(String.class);
        return LocalDate.parse(content, dateFormatter);
    }
}
