package org.wheelsshop.repository.redis;

import java.util.List;

public interface RedisRepository<T, D> {
    void save(T t, long id);

    D getById(long id) throws IllegalStateException;

    List<D> findAll() throws IllegalStateException;

    void deleteById(long id);
}
