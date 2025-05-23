package org.wheelsshop.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface Controller<T> {
    void save(T t) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    List<T> findAll();

    T findById(long id) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    void deleteById(long id);
}
