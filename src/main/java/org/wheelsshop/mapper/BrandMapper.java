package org.wheelsshop.mapper;

import org.mapstruct.Mapper;
import org.wheelsshop.dto.BrandDto;
import org.wheelsshop.dto.Dto;
import org.wheelsshop.entities.Brand;
import org.wheelsshop.request.BrandRequest;

@Mapper(componentModel = "spring")
public interface BrandMapper extends EntityMapper<Brand> {
    Brand fromBrandRequest(BrandRequest brandRequest);

    BrandDto toDto(Brand brand);
}
