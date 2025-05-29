package org.wheelsshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wheelsshop.dto.CarDto;
import org.wheelsshop.entities.Car;
import org.wheelsshop.mapper.CarMapper;
import org.wheelsshop.repository.jpa.CarJpaRepository;
import org.wheelsshop.repository.redis.CarRedisRepository;
import org.wheelsshop.request.CarRequest;

@Service
public class CarService extends AbstractService<CarDto, Car, CarRequest> {
    @Autowired
    public CarService(CarJpaRepository carJpaRepository,
                      CarRedisRepository carRedisRepository,
                      CarMapper carMapper) {
        super(carJpaRepository, carRedisRepository, Car.class, carMapper);
    }
}
