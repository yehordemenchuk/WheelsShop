package org.wheelsshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wheelsshop.entities.Order;
import org.wheelsshop.repository.OrderJpaRepository;

@Service
public class OrderService extends AbstractService<Order> {
    @Autowired
    public OrderService(OrderJpaRepository repository) {
        super(repository);
    }
}
