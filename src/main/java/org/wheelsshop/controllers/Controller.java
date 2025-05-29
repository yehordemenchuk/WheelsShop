package org.wheelsshop.controllers;

import org.apache.coyote.BadRequestException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface Controller<D, T, R> {
    void save(R r) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, BadRequestException;

    List<D> findAll();

    D findById(long id) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    void deleteById(long id);
}
