package org.wheelsshop.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wheelsshop.entities.Brand;
import org.wheelsshop.entities.Car;
import org.wheelsshop.entities.User;
import org.wheelsshop.exceptions.EntityNotFoundException;
import org.wheelsshop.repository.jpa.BrandJpaRepository;
import org.wheelsshop.repository.jpa.CarJpaRepository;
import org.wheelsshop.repository.jpa.UserJpaRepository;

import java.util.Objects;

@Component
public class MapperHelper {
    private final UserJpaRepository userJpaRepository;
    private final CarJpaRepository carJpaRepository;
    private final BrandJpaRepository brandJpaRepository;

    @Autowired
    public MapperHelper(UserJpaRepository userJpaRepository,
                        CarJpaRepository carJpaRepository,
                        BrandJpaRepository brandJpaRepository) {
        this.userJpaRepository = userJpaRepository;

        this.carJpaRepository = carJpaRepository;

        this.brandJpaRepository = brandJpaRepository;
    }

    public Brand getBrand(Long id) throws EntityNotFoundException {
        return brandJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Car getCar(Long id) throws EntityNotFoundException {
        return carJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User getUser(Long id) throws EntityNotFoundException {
        if (Objects.isNull(id)) {
            return null;
        }

        return userJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Long getUserId(User user) {
        if (Objects.isNull(user)) {
            return null;
        }

        return user.getId();
    }

    public Long getCarId(Car car) {
        return car.getId();
    }

    public Long getBrandId(Brand brand) {
        return brand.getId();
    }
}
