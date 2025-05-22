package org.wheelsshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wheelsshop.entities.Brand;
import org.wheelsshop.entities.Car;
import org.wheelsshop.entities.User;
import org.wheelsshop.exceptions.EntityNotFoundException;
import org.wheelsshop.mapper.CarMapper;
import org.wheelsshop.repository.jpa.BrandJpaRepository;
import org.wheelsshop.repository.jpa.CarJpaRepository;
import org.wheelsshop.repository.jpa.UserJpaRepository;
import org.wheelsshop.repository.redis.CarRedisRepository;
import org.wheelsshop.request.CarRequest;
import org.wheelsshop.request.Request;

import java.lang.reflect.InvocationTargetException;

@Service
public class CarService extends AbstractService<Car> {
    private final UserJpaRepository userRepository;
    private final BrandJpaRepository brandRepository;

    @Autowired
    public CarService(CarJpaRepository carJpaRepository,
                      CarRedisRepository carRedisRepository,
                      CarMapper carMapper,
                      UserJpaRepository userRepository,
                      BrandJpaRepository brandRepository) {

        super(carJpaRepository, carRedisRepository, Car.class, carMapper);

        this.userRepository = userRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public void save(Request<Car> r) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {

        CarRequest carRequest = (CarRequest) r;

        User user = carRequest.userId() != null ?
                userRepository.findById(carRequest.userId()).orElseThrow(EntityNotFoundException::new) : null;

        Brand brand = brandRepository.findById(carRequest.brandId()).orElseThrow(EntityNotFoundException::new);

        Car car = new Car();

        car.setBrand(brand);

        car.setUser(user);

        car.setCarImages(carRequest.carImages());

        car.setCarName(carRequest.carName());

        car.setDescription(carRequest.description());

        car.setPrice(carRequest.price());

        car.setCount(carRequest.count());

        getJpaRepository().save(car);

        getRedisRepository().deleteById(getEntityId(car));
    }
}
