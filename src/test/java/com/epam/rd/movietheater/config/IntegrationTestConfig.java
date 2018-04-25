package com.epam.rd.movietheater.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.epam.rd.movietheater",
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        value = {ApplicationConfig.class, WebMvcConfig.class, WebMvcInitializer.class})
        })
public class IntegrationTestConfig {
}
