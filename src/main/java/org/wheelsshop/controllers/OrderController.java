package org.wheelsshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.wheelsshop.dto.OrderDTO;
import org.wheelsshop.mapper.OrderMapper;
import org.wheelsshop.services.OrderService;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService service, OrderMapper mapper) {
        this.orderService = service;

        this.orderMapper = mapper;
    }

    @PostMapping("/save")
    public void saveOrder(@RequestBody OrderDTO orderDTO) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {

        orderService.save(orderMapper.mapToOrder(orderDTO));
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable long id) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {

        return orderMapper.mapToOrderDTO(orderService.findById(id));
    }

    @GetMapping("/all")
    public List<OrderDTO> getAllOrders() {
        return orderService
                .findAll()
                .stream()
                .map(orderMapper::mapToOrderDTO)
                .toList();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrderById(@PathVariable long id) {
        orderService.deleteById(id);
    }
}
