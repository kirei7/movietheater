package com.epam.rd.movietheater.service.auditorium.source;

import com.epam.rd.movietheater.model.entity.Auditorium;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    private Gson gson = new GsonBuilder().create();

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
                .map(e -> mapFromEntry(e))
                .collect(toSet());
    }

    private Auditorium mapFromEntry(Map.Entry<Object, Object> entry) {
        String name = ((String) entry.getKey()).replace('_', ' ');
        try {
            Auditorium newAuditorium = gson.fromJson((String) entry.getValue(), Auditorium.class);
            newAuditorium.setName(name);
            return newAuditorium;
        } catch (Exception ex) {
            throw new WrongAuditoriumSourceFileFormatException("invalid json");
        }
    }
}
