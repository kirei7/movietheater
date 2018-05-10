package com.epam.rd.movietheater.util.mapper;

import com.epam.rd.movietheater.model.entity.IdentifiableEntity;

public interface EntityMapper<T extends IdentifiableEntity, Dto> {
    T toEntity(Dto dto);
}
