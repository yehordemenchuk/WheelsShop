package org.wheelsshop.request;

import org.wheelsshop.entities.Brand;

public record BrandRequest(String brandName,
                           String emblem) implements Request<Brand> {
}
