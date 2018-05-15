package com.epam.rd.movietheater.config;

import com.epam.rd.movietheater.exception.ManagerInfoFileNotPresentException;
import com.epam.rd.movietheater.model.dto.EventDto;
import com.epam.rd.movietheater.model.dto.UserDto;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.security.UserRole;
import com.epam.rd.movietheater.service.IdentifiableEntityService;
import com.epam.rd.movietheater.service.auditorium.AuditoriumService;
import com.epam.rd.movietheater.service.event.EventService;
import com.epam.rd.movietheater.service.facade.BookingFacade;
import com.epam.rd.movietheater.service.facade.UserFacade;
import com.epam.rd.movietheater.service.user.UserService;
import com.epam.rd.movietheater.util.batch.update.BatchUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
public class BaseConfig {

    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;
    @Autowired
    private BookingFacade bookingFacade;
    @Autowired
    private AuditoriumService auditoriumService;
    @Autowired
    private UserFacade userFacade;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    private Map<Class, IdentifiableEntityService> entityServices() {
        Map<Class, IdentifiableEntityService> map = new HashMap<>();
        map.put(User.class, userService);
        map.put(Event.class, eventService);
        return map;
    }

    @Autowired
    private BatchUpdater<Event, EventDto> eventBatchUpdater;
    @Autowired
    private BatchUpdater<User, UserDto> userBatchUpdater;
    @Bean
    public Map<Class, BatchUpdater> batchUpdaters() {
        Map<Class, BatchUpdater> updaters = new HashMap<>();
        updaters.put(Event.class, eventBatchUpdater);
        updaters.put(User.class, userBatchUpdater);
        return updaters;
    }

    @PostConstruct
    protected void registerManager() {
        Resource resource = new ClassPathResource("bookingManager.properties");
        try {
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            UserDto manager = new UserDto();
            manager.setNickName(props.getProperty("manager.nickName"));
            manager.setPassword(props.getProperty("manager.password"));
            userFacade.registerUser(manager, UserRole.BOOKING_MANAGER);
        } catch (IOException ex) {
            throw new ManagerInfoFileNotPresentException();
        }
    }
}
