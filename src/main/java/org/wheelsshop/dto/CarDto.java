package org.wheelsshop.dto;

import java.math.BigDecimal;
import java.util.List;

public record CarDto(Long id,
                     String carName,
                     String description,
                     BigDecimal price,
                     List<String> carImages,
                     Integer count,
                     Long brandId,
                     Long userId) {

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
