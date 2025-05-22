package org.wheelsshop.controllers;

import org.springframework.web.bind.annotation.*;
import org.wheelsshop.dto.Dto;
import org.wheelsshop.request.Request;
import org.wheelsshop.services.ServiceContract;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class AbstractController<T> implements Controller<T> {
    private final ServiceContract<T> service;

    protected AbstractController(ServiceContract<T> service) {
        this.service = service;
    }



    @PostMapping("/save")
    @Override
    public void save(@RequestBody Request<T> r) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {
        service.save(r);
    }

    @GetMapping("/all")
    @Override
    public List<Dto<T>> findAll() {
        return service.findAll();
    }

    @GetMapping("/find/{id}")
    @Override
    public Dto<T> findById(@PathVariable long id) throws InvocationTargetException, NoSuchMethodException,
            IllegalAccessException {

        return service.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void deleteById(@PathVariable long id) {
        service.deleteById(id);
    }
}
