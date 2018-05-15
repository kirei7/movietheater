package com.epam.rd.movietheater.service.auditorium.source;

import com.epam.rd.movietheater.exception.AuditoriumSourceFileNotPresentException;
import com.epam.rd.movietheater.exception.WrongAuditoriumSourceFileFormatException;
import com.epam.rd.movietheater.model.entity.Auditorium;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Component("AuditoriumSource")
public class PropertyBasedAuditoriumSource implements AuditoriumSource {

    private Set<Auditorium> auditoriums;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Set<Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public PropertyBasedAuditoriumSource(@Value("${auditoriums.source}") String fileName) {
        Resource resource = new ClassPathResource(fileName);
        try {
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            auditoriums = fetchAuditoriums(props);
        } catch (IOException ex) {
            throw new AuditoriumSourceFileNotPresentException(fileName);
        }
    }

    private Set<Auditorium> fetchAuditoriums(Properties props) {
        return props.entrySet().stream()
                .map(this::mapFromEntry)
                .collect(toSet());
    }

    private Auditorium mapFromEntry(Map.Entry<Object, Object> entry) {
        String name = ((String) entry.getKey()).replace('_', ' ');
        try {
            Auditorium newAuditorium = objectMapper.readValue((String) entry.getValue(), Auditorium.class);
            newAuditorium.setName(name);
            return newAuditorium;
        } catch (Exception ex) {
            throw new WrongAuditoriumSourceFileFormatException("invalid json");
        }
    }
}
