package org.wheelsshop.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wheelsshop.exceptions.EntityNotFoundException;

import java.util.List;

public abstract class AbstractService<T> implements ServiceContract<T> {
    private final JpaRepository<T, Long> repository;

    protected AbstractService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    @Override
    public void save(T t) {
        repository.save(t);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findById(long id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteById(long id) throws EntityNotFoundException {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        repository.deleteById(id);
    }
}
