package com.epam.rd.movietheater.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan("com.epam.rd.movietheater")
@PropertySource("classpath:application.properties")
@Import({Beans.class, AspectConfig.class, PersistenceConfig.class})
public class ApplicationConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
