package org.wheelsshop.services;

import org.wheelsshop.exceptions.EntityNotFoundException;

import java.util.List;

public interface ServiceContract<T> {
    void save(T t);

    List<T> findAll();

    T findById(long id) throws EntityNotFoundException;

    void deleteById(long id) throws EntityNotFoundException;
}
