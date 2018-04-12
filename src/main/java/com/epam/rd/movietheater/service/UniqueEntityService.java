package com.epam.rd.movietheater.service;

import com.epam.rd.movietheater.model.entity.UniqueEntity;

import java.util.List;
import java.util.Optional;

public interface UniqueEntityService<T extends UniqueEntity> {
    T save(T entity);
    boolean remove(T entity);
    Optional<T> getById(Long id);
    List<T> getAll();
}
