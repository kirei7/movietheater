package com.epam.rd.movietheater.dao;

import com.epam.rd.movietheater.model.entity.IdentifiableEntity;

import java.util.List;
import java.util.Optional;

public interface IdentifiableDao<T extends IdentifiableEntity> {
    T save(T t);
    boolean remove(T t);
    Optional<T> getById(Long id);
    List<T> findAll();
}
