package com.epam.rd.movietheater.config;

import com.epam.rd.movietheater.model.Auditorium;
import com.epam.rd.movietheater.util.AuditoriumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Set;

@Configuration
@ComponentScan("com.epam.rd.movietheater")
@PropertySource("classpath:application.properties")
@Import(Beans.class)
public class ApplicationConfig {

}
