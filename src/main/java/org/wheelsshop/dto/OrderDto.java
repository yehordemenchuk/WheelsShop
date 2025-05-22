package org.wheelsshop.dto;

import org.wheelsshop.entities.Order;
import org.wheelsshop.entities.Status;

import java.math.BigDecimal;
import java.util.Date;

public record OrderDto(Long id,
                       Status status,
                       Integer count,
                       BigDecimal amount,
                       Date createdAt,
                       UserDto user,
                       CarDto car) implements Dto<Order> {
}
