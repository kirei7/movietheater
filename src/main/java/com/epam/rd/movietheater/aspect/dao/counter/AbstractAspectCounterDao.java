package com.epam.rd.movietheater.aspect.dao.counter;

import com.epam.rd.movietheater.model.entity.IdentifiableEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.stream.Collectors.toList;

public abstract class AbstractAspectCounterDao<T extends IdentifiableEntity> implements AspectCounterDao<T> {

    protected JdbcTemplate jdbcTemplate;
    protected Map<Long, AtomicLong> cache = new ConcurrentHashMap<>();
    protected AtomicLong bufferCounter = new AtomicLong();
    protected static final int BUFFER_SIZE = 5;
    protected final String tableName;

    public AbstractAspectCounterDao(JdbcTemplate jdbcTemplate, String tableName) {
        this.jdbcTemplate = jdbcTemplate;
        this.tableName = tableName;
    }

    @PostConstruct
    protected void loadDataToCache() {
        jdbcTemplate.query(
                "SELECT * FROM " + tableName,
                (rs, rowNum) -> cache.put(rs.getLong("id"), new AtomicLong(rs.getLong("counter"))));
    }

    @PreDestroy
    protected void destructor() {
        dumpToDatabase();
    }

    @Override
    public void incrementFor(T item) {
        AtomicLong counter = cache.computeIfAbsent(item.getId(), k -> new AtomicLong());
        counter.incrementAndGet();
        if (bufferCounter.incrementAndGet() > BUFFER_SIZE) {
            dumpToDatabase();
            bufferCounter.set(0);
        }
    }
    private void dumpToDatabase() {
        String query = "REPLACE INTO " + tableName + " (id, counter) VALUES(?, ?)";
        jdbcTemplate.batchUpdate(
                query,
                cache.entrySet().stream().map(entry -> new Object[]{entry.getKey(), entry.getValue().get()}).collect(toList())
        );
    }

    @Override
    public Long getValueFor(T item) {
        return cache.getOrDefault(item.getId(), new AtomicLong()).get();
    }
}
