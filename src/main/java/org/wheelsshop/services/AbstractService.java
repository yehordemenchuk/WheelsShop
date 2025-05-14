package org.wheelsshop.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wheelsshop.exceptions.EntityNotFoundException;
import org.wheelsshop.repository.redis.RedisRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

public abstract class AbstractService<T> implements ServiceContract<T> {
    private final JpaRepository<T, Long> jpaRepository;
    private final RedisRepository<T> redisRepository;
    private final Class<T> entityClass;

    protected AbstractService(JpaRepository<T, Long> jpaRepository,
                              RedisRepository<T> redisRepository,
                              Class<T> entityClass) {

        this.jpaRepository = jpaRepository;

        this.redisRepository = redisRepository;

        this.entityClass = entityClass;
    }

    private Long getEntityId(T entity) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {

        Method method = entityClass.getMethod("getId");

        return (Long) method.invoke(entity);
    }

    @Override
    public void save(T t) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        jpaRepository.save(t);

        redisRepository.deleteById(getEntityId(t));
    }

    @Override
    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public T findById(long id) throws EntityNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {

        T entity = redisRepository.getById(id);

        if (Objects.nonNull(entity)) {
            return entity;
        }

        entity = jpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        redisRepository.save(entity, getEntityId(entity));

        return entity;
    }

    @Override
    public void deleteById(long id) throws EntityNotFoundException {
        if (!jpaRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        jpaRepository.deleteById(id);

        redisRepository.deleteById(id);
    }
}
