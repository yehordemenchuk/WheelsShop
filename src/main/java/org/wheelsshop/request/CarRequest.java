package org.wheelsshop.request;

import org.wheelsshop.entities.Car;

import java.math.BigDecimal;
import java.util.List;

public record CarRequest(String carName,
                         String description,
                         BigDecimal price,
                         Integer count,
                         List<String> carImages,
                         Long brandId,
                         Long userId) implements Request<Car> {
}
