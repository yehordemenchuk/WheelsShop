package org.wheelsshop.services;

import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wheelsshop.exceptions.EntityNotFoundException;
import org.wheelsshop.mapper.MapperContract;
import org.wheelsshop.repository.redis.RedisRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

@Getter
public abstract class AbstractService<D, T, R> implements ServiceContract<D, T, R> {
    private final JpaRepository<T, Long> jpaRepository;
    private final RedisRepository<T, D> redisRepository;
    private final Class<T> entityClass;
    private final MapperContract<D, T, R> mapper;

    protected AbstractService(JpaRepository<T, Long> jpaRepository,
                              RedisRepository<T, D> redisRepository,
                              Class<T> entityClass,
                              MapperContract<D, T, R> mapper) {

        this.jpaRepository = jpaRepository;

        this.redisRepository = redisRepository;

        this.entityClass = entityClass;

        this.mapper = mapper;
    }

    private Long getEntityId(T entity) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {

        Method method = entityClass.getMethod("getId");

        return (Long) method.invoke(entity);
    }

    @Override
    public void save(R r) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        T entity = jpaRepository.save(mapper.fromRequest(r));

        redisRepository.deleteById(getEntityId(entity));
    }

    @Override
    public List<D> findAll() {
        return jpaRepository
                .findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public D findById(long id) throws EntityNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {

        D dto = redisRepository.getById(id);

        if (Objects.nonNull(dto)) {
            return dto;
        }

        T entity = jpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        redisRepository.save(entity, getEntityId(entity));

        return mapper.toDto(entity);
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
