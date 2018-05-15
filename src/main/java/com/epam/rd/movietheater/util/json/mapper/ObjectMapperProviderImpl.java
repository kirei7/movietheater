package com.epam.rd.movietheater.util.json.mapper;

import com.epam.rd.movietheater.util.json.LocalDateTimeToStringConverter;
import com.epam.rd.movietheater.util.json.StringToLocalDateTimeConverter;
import com.epam.rd.movietheater.util.json.formatter.DateFormatterProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("ObjectMapperProvider")
public class ObjectMapperProviderImpl implements ObjectMapperProvider {

    private DateFormatterProvider dateFormatterProvider;
    private StringToLocalDateTimeConverter stringToLocalDateTimeConverter;
    private LocalDateTimeToStringConverter localDateTimeToStringConverter;

    @Autowired
    public ObjectMapperProviderImpl(DateFormatterProvider dateFormatterProvider, StringToLocalDateTimeConverter converter, LocalDateTimeToStringConverter localDateTimeToStringConverter) {
        this.dateFormatterProvider = dateFormatterProvider;
        this.stringToLocalDateTimeConverter = converter;
        this.localDateTimeToStringConverter = localDateTimeToStringConverter;
    }

    @Override
    public ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDateTime.class, stringToLocalDateTimeConverter);
        module.addSerializer(LocalDateTime.class, localDateTimeToStringConverter);
        mapper.registerModule(module);
        return mapper;
    }
}
