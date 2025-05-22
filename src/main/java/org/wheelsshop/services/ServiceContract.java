package org.wheelsshop.services;

import org.wheelsshop.dto.Dto;
import org.wheelsshop.exceptions.EntityNotFoundException;
import org.wheelsshop.request.Request;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ServiceContract<T> {
    void save(Request<T> r) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    List<Dto<T>> findAll();

    Dto<T> findById(long id) throws EntityNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException;

    void deleteById(long id) throws EntityNotFoundException;
}
