package org.wheelsshop.dto;

import org.wheelsshop.entities.Brand;

public record BrandDto(Long id,
                       String brandName,
                       String emblem) implements Dto<Brand> {
}
