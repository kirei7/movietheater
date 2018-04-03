package com.epam.rd.movietheater.dao.impl;

import com.epam.rd.movietheater.dao.UniqueEntityDao;
import com.epam.rd.movietheater.entity.UniqueEntity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractInMemoryDao<T extends UniqueEntity> implements UniqueEntityDao<T> {

    protected AtomicLong counter = new AtomicLong();
    protected Map<Long, T> storage = new ConcurrentHashMap<>();

    @Override
    public T save(T entity) {
        entity.setId(Optional.ofNullable(entity.getId()).orElse(counter.incrementAndGet()));
        return storage.put(entity.getId(), entity);
    }

    @Override
    public boolean remove(T entity) {
        T result = storage.remove(entity.getId());
        return result != null;
    }

    @Override
    public Optional<T> getById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }
}
