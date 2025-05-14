package org.wheelsshop.repository.redis;

import java.util.List;

public interface RedisRepository<T> {
    void save(T t, long id);

    T getById(long id) throws IllegalStateException;

    List<T> findAll() throws IllegalStateException;

    void deleteById(long id);
}
