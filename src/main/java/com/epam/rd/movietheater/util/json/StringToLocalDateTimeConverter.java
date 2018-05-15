package com.epam.rd.movietheater.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class StringToLocalDateTimeConverter extends StdDeserializer<LocalDateTime> {

    private DateTimeFormatter dateTimeFormatter;

    StringToLocalDateTimeConverter(@Value("#{DateFormatterProvider.getFormatter()}") DateTimeFormatter dateTimeFormatter) {
        super(LocalDateTime.class);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String content = p.readValueAs(String.class);
        return LocalDateTime.parse(content, dateTimeFormatter);
    }
}
