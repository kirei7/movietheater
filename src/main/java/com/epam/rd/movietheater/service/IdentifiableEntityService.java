package com.epam.rd.movietheater.service;

import com.epam.rd.movietheater.model.entity.IdentifiableEntity;

import java.util.List;
import java.util.Optional;

public interface IdentifiableEntityService<T extends IdentifiableEntity> {
    T save(T entity);
    boolean remove(T entity);
    Optional<T> getById(Long id);
    List<T> getAll();
}
