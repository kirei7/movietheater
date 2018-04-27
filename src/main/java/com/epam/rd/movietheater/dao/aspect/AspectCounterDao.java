package com.epam.rd.movietheater.dao.aspect;

import com.epam.rd.movietheater.model.entity.IdentifiableEntity;

public interface AspectCounterDao<T extends IdentifiableEntity> {
    void incrementFor(T item);
    Long getValueFor(T item);
}
