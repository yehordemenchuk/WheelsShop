package org.wheelsshop.dto;

import org.wheelsshop.entities.Car;

import java.math.BigDecimal;
import java.util.List;

public record CarDto(Long id,
                     String carName,
                     String description,
                     BigDecimal price,
                     Integer count,
                     List<String> carImages,
                     BrandDto brand,
                     UserDto user) implements Dto<Car> {
}
