package org.wheelsshop.repository.redis;

import lombok.Getter;
import org.springframework.data.redis.core.RedisTemplate;
import org.wheelsshop.mapper.MapperContract;

import java.util.*;

@Getter
public abstract class AbstractRedisRepository<D, T, R> implements RedisRepository<T, D> {
    private final RedisTemplate<String, Object> redisTemplate;
    private final String prefix;
    private final Class<D> clazz;
    private final MapperContract<D, T, R> mapper;

    protected AbstractRedisRepository(RedisTemplate<String, Object> redisTemplate,
                                      String prefix, Class<D> clazz, MapperContract<D, T, R> mapper) {
        this.redisTemplate = redisTemplate;

        this.prefix = prefix;

        this.clazz = clazz;

        this.mapper = mapper;
    }

    @Override
    public void save(T t, long id) {
        System.out.println(mapper.toDto(t));

        redisTemplate.opsForValue().set(prefix + id, mapper.toDto(t));
    }

    @Override
    public D getById(long id) throws IllegalStateException {
        Object obj = redisTemplate.opsForValue().get(prefix + id);

        if (clazz != null && clazz.isInstance(obj)) {
            return clazz.cast(obj);
        } else if (clazz != null && obj != null) {
            throw new IllegalStateException();
        }

        return null;
    }

    @Override
    public List<D> findAll() {
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
