package com.epam.rd.movietheater.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.epam.rd.movietheater")
@PropertySource("classpath:application.properties")
@Import(Beans.class)
public class ApplicationConfig {

}
