package com.epam.rd.movietheater.util.json.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface ObjectMapperProvider {
    ObjectMapper getObjectMapper();
}
