package org.wheelsshop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wheelsshop.dto.OrderDto;
import org.wheelsshop.entities.Order;
import org.wheelsshop.request.OrderRequest;
import org.wheelsshop.services.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController extends AbstractController<OrderDto, Order, OrderRequest> {
    public OrderController(OrderService service) {
        super(service);
    }
}
