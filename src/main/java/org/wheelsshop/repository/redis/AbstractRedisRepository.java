package org.wheelsshop.repository.redis;

import lombok.Getter;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

@Getter
public abstract class AbstractRedisRepository<T> implements RedisRepository<T> {
    private final RedisTemplate<String, Object> redisTemplate;
    private final String prefix;
    private final Class<T> clazz;

    protected AbstractRedisRepository(RedisTemplate<String, Object> redisTemplate,
                                      String prefix, Class<T> clazz) {
        this.redisTemplate = redisTemplate;

        this.prefix = prefix;

        this.clazz = clazz;
    }

    @Override
    public void save(T t, long id) {
        redisTemplate.opsForValue().set(prefix + id, t);
    }

    @Override
    public T getById(long id) throws IllegalStateException {
        Object obj = redisTemplate.opsForValue().get(prefix + id);

        if (clazz.isInstance(obj)) {
            return clazz.cast(obj);
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public List<T> findAll() {
        Set<String> keys = redisTemplate.keys(prefix + "*");

        if (Objects.isNull(keys) || keys.isEmpty()) {
            return Collections.emptyList();
        }

        List<Object> values = redisTemplate.opsForValue().multiGet(keys);

        if (Objects.isNull(values) || values.isEmpty()) {
            return Collections.emptyList();
        }

        values.forEach(obj -> {
            if (!clazz.isInstance(obj)) {
                throw new IllegalStateException();
            }
        });

        return values
                .stream()
                .map(clazz::cast)
                .toList();
    }

    @Override
    public void deleteById(long id) {
        redisTemplate.delete(prefix + id);
    }
}
