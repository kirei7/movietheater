package com.epam.rd.movietheater.dao;

import com.epam.rd.movietheater.model.entity.UniqueEntity;

import java.util.List;
import java.util.Optional;

public interface UniqueEntityDao<T extends UniqueEntity> {
    T save(T user);
    boolean remove(T user);
    Optional<T> getById(Long id);
    List<T> findAll();
}
