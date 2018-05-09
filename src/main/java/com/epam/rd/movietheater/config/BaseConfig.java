package com.epam.rd.movietheater.config;

import com.epam.rd.movietheater.exception.ManagerInfoFileNotPresentException;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.security.UserRole;
import com.epam.rd.movietheater.service.IdentifiableEntityService;
import com.epam.rd.movietheater.service.auditorium.AuditoriumService;
import com.epam.rd.movietheater.service.event.EventService;
import com.epam.rd.movietheater.service.facade.BookingFacade;
import com.epam.rd.movietheater.service.facade.UserFacade;
import com.epam.rd.movietheater.service.user.UserService;
import com.epam.rd.movietheater.util.batchupload.BatchUploader;
import com.epam.rd.movietheater.util.batchupload.JsonBatchUploader;
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

    /*@EventListener({ContextRefreshedEvent.class})
    public void init() {
        Event event = new Event("Film one",
                LocalDateTime.now().plusHours(2),
                15.00,
                Event.Rating.HIGH,
                auditoriumService.getAll().get(0)
        );
        eventService.save(event);
        bookingFacade.buyTickets(event.getId(), userService.getUserByNickName("user"), new long[]{2, 3, 4, 5});
    }*/

    @PostConstruct
    protected void registerManager() {
        Resource resource = new ClassPathResource("bookingManager.properties");
        try {
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            User manager = new User(
                    props.getProperty("manager.nickName"),
                    props.getProperty("manager.password")
            );
            manager.addRole(UserRole.BOOKING_MANAGER);
            userFacade.registerUser(manager);
        } catch (IOException ex) {
            throw new ManagerInfoFileNotPresentException();
        }
    }
}
