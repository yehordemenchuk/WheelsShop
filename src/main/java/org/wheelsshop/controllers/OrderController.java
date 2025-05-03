package org.wheelsshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public OrderController(OrderService service) {
        this.orderService = service;
    }

    @PostMapping("/save")
    public void saveOrder(@RequestBody OrderDTO orderDTO) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {

        orderService.save(OrderMapper.INSTANCE.mapToOrder(orderDTO));
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable long id) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {

        return OrderMapper.INSTANCE.mapToOrderDTO(orderService.findById(id));
    }

    @GetMapping("/all")
    public List<OrderDTO> getAllOrders() {
        return orderService
                .findAll()
                .stream()
                .map(OrderMapper.INSTANCE::mapToOrderDTO)
                .toList();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrderById(@PathVariable long id) {
        orderService.deleteById(id);
    }
}
