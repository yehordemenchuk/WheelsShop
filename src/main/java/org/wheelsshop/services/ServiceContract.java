package org.wheelsshop.services;

import org.wheelsshop.exceptions.EntityNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ServiceContract<D, T, R> {
    void save(R r) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    List<D> findAll();

    D findById(long id) throws EntityNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    void deleteById(long id) throws EntityNotFoundException;
}
