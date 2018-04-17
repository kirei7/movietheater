package com.epam.rd.movietheater.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ApplicationConfig.class, TestDataConfig.class})
public class IntegrationTestConfig {
}
