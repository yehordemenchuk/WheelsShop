package org.wheelsshop.controllers;

import java.util.List;

public interface Controller<T> {
    void save(T t);

    List<T> findAll();

    T findById(long id);

    void deleteById(long id);
}
