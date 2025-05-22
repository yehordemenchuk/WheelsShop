package org.wheelsshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import org.wheelsshop.request.Request;

import java.lang.reflect.InvocationTargetException;

@Service
public class OrderService extends AbstractService<Order> {
    private final UserJpaRepository userRepository;
    private final CarJpaRepository carRepository;

    @Autowired
    public OrderService(OrderJpaRepository orderJpaRepository,
                        OrderRedisRepository orderRedisRepository,
                        OrderMapper orderMapper,
                        UserJpaRepository userRepository,
                        CarJpaRepository orderRepository) {

        super(orderJpaRepository, orderRedisRepository, Order.class, orderMapper);

        this.userRepository = userRepository;

        this.carRepository = orderRepository;
    }

    @Override
    public void save(Request<Order> r) throws InvocationTargetException, NoSuchMethodException,
            IllegalAccessException {

        OrderRequest orderRequest = (OrderRequest) r;

        User user = userRepository.findById(orderRequest.userId())
                .orElseThrow(EntityNotFoundException::new);

        Car car = carRepository.findById(orderRequest.userId())
                .orElseThrow(EntityNotFoundException::new);

        Order order = new Order();

        order.setUser(user);

        order.setCar(car);

        order.setAmount(orderRequest.amount());

        order.setCount(orderRequest.count());

        order.setStatus(orderRequest.status());

        order.setCreatedAt(orderRequest.createdAt());

        getJpaRepository().save(order);

        getRedisRepository().deleteById(getEntityId(order));
    }
}
