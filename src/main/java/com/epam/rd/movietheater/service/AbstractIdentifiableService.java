package com.epam.rd.movietheater.service;

import com.epam.rd.movietheater.dao.IdentifiableDao;
import com.epam.rd.movietheater.model.entity.IdentifiableEntity;

import java.util.List;
import java.util.Optional;

public abstract class AbstractIdentifiableService<T extends IdentifiableEntity, D extends IdentifiableDao<T>> implements IdentifiableEntityService<T> {

    protected D dao;

    protected AbstractIdentifiableService(D dao) {
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
