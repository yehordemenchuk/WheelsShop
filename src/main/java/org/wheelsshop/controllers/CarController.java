package org.wheelsshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wheelsshop.entities.Car;
import org.wheelsshop.services.CarService;

@RestController
@RequestMapping("/api/v1/car")
public class CarController extends AbstractController<Car> {

    @Autowired
    public CarController(CarService service) {
        super(service);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void save(@RequestBody Car car) {
        super.save(car);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable long id) {
        super.deleteById(id);
    }
}
