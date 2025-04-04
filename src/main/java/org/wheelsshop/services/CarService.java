package org.wheelsshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wheelsshop.entities.Car;
import org.wheelsshop.repository.CarJpaRepository;

@Service
public class CarService extends AbstractService<Car> {
    @Autowired
    public CarService(CarJpaRepository repository) {
        super(repository);
    }
}
