package org.wheelsshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wheelsshop.entities.Order;
import org.wheelsshop.repository.jpa.OrderJpaRepository;
import org.wheelsshop.repository.redis.OrderRedisRepository;

@Service
public class OrderService extends AbstractService<Order> {
    @Autowired
    public OrderService(OrderJpaRepository orderJpaRepository,
                        OrderRedisRepository orderRedisRepository) {
        super(orderJpaRepository, orderRedisRepository, Order.class);
    }
}
