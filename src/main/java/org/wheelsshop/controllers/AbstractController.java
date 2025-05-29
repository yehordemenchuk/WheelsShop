package org.wheelsshop.controllers;

import org.springframework.web.bind.annotation.*;
import org.wheelsshop.services.ServiceContract;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class AbstractController<D, T, R> implements Controller<D, T, R> {
    private final ServiceContract<D, T, R> service;

    protected AbstractController(ServiceContract<D, T, R> service) {
        this.service = service;
    }

    @PostMapping("/save")
    @Override
    public void save(@RequestBody R r) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {

        service.save(r);
    }

    @GetMapping("/all")
    @Override
    public List<D> findAll() {
        return service.findAll();
    }

    @GetMapping("/find/{id}")
    @Override
    public D findById(@PathVariable long id) throws InvocationTargetException, NoSuchMethodException,
            IllegalAccessException {

        return service.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void deleteById(@PathVariable long id) {
        service.deleteById(id);
    }
}
