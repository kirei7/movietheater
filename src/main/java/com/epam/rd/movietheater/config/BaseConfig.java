package com.epam.rd.movietheater.config;

import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.IdentifiableEntityService;
import com.epam.rd.movietheater.service.event.EventService;
import com.epam.rd.movietheater.service.user.UserService;
import com.epam.rd.movietheater.util.batchupload.BatchUploader;
import com.epam.rd.movietheater.util.batchupload.JsonBatchUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
public class BaseConfig {

    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public BatchUploader batchUploader() {
        return new JsonBatchUploader(entityServices());
    }

    private Map<Class, IdentifiableEntityService> entityServices() {
        Map<Class, IdentifiableEntityService> map = new HashMap<>();
        map.put(User.class, userService);
        map.put(Event.class, eventService);
        return map;
    }
}
