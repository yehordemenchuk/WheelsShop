package org.wheelsshop.controllers;

import org.wheelsshop.dto.Dto;
import org.wheelsshop.request.Request;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface Controller<T> {
    void save(Request<T> r) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    List<Dto<T>> findAll();

    Dto<T> findById(long id) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    void deleteById(long id);
}
