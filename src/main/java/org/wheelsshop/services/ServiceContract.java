package org.wheelsshop.services;

import org.wheelsshop.exceptions.EntityNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ServiceContract<T> {
    void save(T t) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    List<T> findAll();

    T findById(long id) throws EntityNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException;

    void deleteById(long id) throws EntityNotFoundException;
}
