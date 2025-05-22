package org.wheelsshop.services;

import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wheelsshop.dto.Dto;
import org.wheelsshop.exceptions.EntityNotFoundException;
import org.wheelsshop.mapper.EntityMapper;
import org.wheelsshop.repository.redis.RedisRepository;
import org.wheelsshop.request.Request;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public abstract class AbstractService<T> implements ServiceContract<T> {
    private final JpaRepository<T, Long> jpaRepository;
    private final RedisRepository<T> redisRepository;
    private final EntityMapper<T> entityMapper;
    private final Class<T> entityClass;

    protected AbstractService(JpaRepository<T, Long> jpaRepository,
                              RedisRepository<T> redisRepository,
                              Class<T> entityClass,
                              EntityMapper<T> entityMapper) {

        this.jpaRepository = jpaRepository;

        this.redisRepository = redisRepository;

        this.entityClass = entityClass;

        this.entityMapper = entityMapper;
    }

    public Long getEntityId(T entity) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {

        Method method = entityClass.getMethod("getId");

        return (Long) method.invoke(entity);
    }

    @Override
    public List<Dto<T>> findAll() {
        List<T> entities = jpaRepository.findAll();

        return entities.stream().map(entityMapper::toDto).toList();
    }

    @Override
    public Dto<T> findById(long id) throws EntityNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {

        T entity = redisRepository.getById(id);

        if (Objects.nonNull(entity)) {
            return entityMapper.toDto(entity);
        }

        entity = jpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        redisRepository.save(entity, getEntityId(entity));

        return entityMapper.toDto(entity);
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
