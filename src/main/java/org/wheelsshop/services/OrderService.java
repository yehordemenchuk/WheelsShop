package org.wheelsshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.wheelsshop.dto.OrderDto;
import org.wheelsshop.entities.Car;
import org.wheelsshop.entities.Order;
import org.wheelsshop.entities.User;
import org.wheelsshop.exceptions.EntityNotFoundException;
import org.wheelsshop.mapper.OrderMapper;
import org.wheelsshop.repository.jpa.CarJpaRepository;
import org.wheelsshop.repository.jpa.OrderJpaRepository;
import org.wheelsshop.repository.jpa.UserJpaRepository;
import org.wheelsshop.repository.redis.OrderRedisRepository;
import org.wheelsshop.request.OrderRequest;

import java.lang.reflect.InvocationTargetException;

@Service
public class OrderService extends AbstractService<OrderDto, Order, OrderRequest> {
    private final CarJpaRepository carJpaRepository;

    @Autowired
    public OrderService(OrderJpaRepository orderJpaRepository,
                        OrderRedisRepository orderRedisRepository,
                        OrderMapper orderMapper,
                        UserJpaRepository userJpaRepository,
                        CarJpaRepository carJpaRepository) {

        super(orderJpaRepository, orderRedisRepository, Order.class, orderMapper);

        this.carJpaRepository = carJpaRepository;
    }

    public String makeOrder(OrderRequest orderRequest) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException, ResponseStatusException {
        super.save(orderRequest);

        User user = getMapper().fromRequest(orderRequest).getUser();

        Car car = carJpaRepository.findById(orderRequest.carId())
                .orElseThrow(EntityNotFoundException::new);

        if (car.getCount() == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car`s count is 0");

        else if (car.getCount() - orderRequest.count() < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car`s count is negative");

        car.setCount(car.getCount() - 1);

        carJpaRepository.save(car);

        return user.getEmail();
    }
}
