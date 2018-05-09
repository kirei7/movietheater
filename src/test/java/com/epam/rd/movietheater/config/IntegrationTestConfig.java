package com.epam.rd.movietheater.config;

import com.epam.rd.movietheater.server.WebMvcInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.epam.rd.movietheater",
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        value = {ApplicationConfig.class, WebMvcConfig.class, WebMvcInitializer.class}),
                @ComponentScan.Filter(
                        type = FilterType.ASPECTJ,
                        pattern = "com.epam.rd.movietheater.controller.*"),
                @ComponentScan.Filter(
                        type = FilterType.ASPECTJ,
                        pattern = "com.epam.rd.movietheater.util.pdf.*")

        })
public class IntegrationTestConfig {
}
