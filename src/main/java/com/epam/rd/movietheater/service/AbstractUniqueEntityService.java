package com.epam.rd.movietheater.service;

import com.epam.rd.movietheater.dao.UniqueEntityDao;
import com.epam.rd.movietheater.model.entity.UniqueEntity;
import com.epam.rd.movietheater.service.UniqueEntityService;

import java.util.List;
import java.util.Optional;

public abstract class AbstractUniqueEntityService<T extends UniqueEntity, D extends UniqueEntityDao<T>> implements UniqueEntityService<T> {

    protected D dao;

    protected AbstractUniqueEntityService(D dao) {
        this.dao = dao;
    }

    @Override
    public T save(T entity) {
        return dao.save(entity);
    }

    @Override
    public boolean remove(T entity) {
        return dao.remove(entity);
    }

    @Override
    public Optional<T> getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public List<T> getAll() {
        return dao.findAll();
    }
}
