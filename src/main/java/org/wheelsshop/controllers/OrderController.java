package org.wheelsshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wheelsshop.entities.Order;
import org.wheelsshop.services.OrderService;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController extends AbstractController<Order> {
    @Autowired
    public OrderController(OrderService service) {
        super(service);
    }
}
