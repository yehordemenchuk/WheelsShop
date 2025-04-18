package org.wheelsshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wheelsshop.entities.Car;
import org.wheelsshop.repository.jpa.CarJpaRepository;
import org.wheelsshop.repository.redis.CarRedisRepository;

@Service
public class CarService extends AbstractService<Car> {
    @Autowired
    public CarService(CarJpaRepository carJpaRepository, CarRedisRepository carRedisRepository) {
        super(carJpaRepository, carRedisRepository, Car.class);
    }
}
