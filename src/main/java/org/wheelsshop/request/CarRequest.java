package org.wheelsshop.request;

import java.math.BigDecimal;
import java.util.List;

public record CarRequest(String carName,
                         String description,
                         BigDecimal price,
                         List<String> carImages,
                         Integer count,
                         Long brandId,
                         Long userId) {
}
