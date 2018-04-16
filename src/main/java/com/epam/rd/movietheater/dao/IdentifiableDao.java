package com.epam.rd.movietheater.dao;

import com.epam.rd.movietheater.model.entity.IdentifiableEntity;

import java.util.List;
import java.util.Optional;

public interface IdentifiableDao<T extends IdentifiableEntity> {
    T save(T user);
    boolean remove(T user);
    Optional<T> getById(Long id);
    List<T> findAll();
}
