package org.wheelsshop.dto;

import org.wheelsshop.entities.Car;
import org.wheelsshop.entities.Status;
import org.wheelsshop.entities.User;

import java.math.BigDecimal;
import java.util.Date;

public record OrderDto(Long id,
                       Status status,
                       Integer count,
                       BigDecimal amount,
                       Date createdAt,
                       Long userId,
                       Long carId) {
}
