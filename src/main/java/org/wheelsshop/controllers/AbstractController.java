package org.wheelsshop.controllers;

import org.springframework.web.bind.annotation.*;
import org.wheelsshop.services.ServiceContract;

import java.util.List;

public abstract class AbstractController<T> implements Controller<T> {
    private final ServiceContract<T> service;

    protected AbstractController(ServiceContract<T> service) {
        this.service = service;
    }



    @PostMapping("/save")
    @Override
    public void save(@RequestBody T t) {
        service.save(t);
    }

    @GetMapping("/all")
    @Override
    public List<T> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Override
    public T findById(@PathVariable long id) {
        return service.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void deleteById(@PathVariable long id) {
        service.deleteById(id);
    }
}
