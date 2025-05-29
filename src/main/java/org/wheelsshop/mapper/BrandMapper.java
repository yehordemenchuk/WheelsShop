package org.wheelsshop.mapper;

import org.mapstruct.Mapper;
import org.wheelsshop.dto.BrandDto;
import org.wheelsshop.entities.Brand;
import org.wheelsshop.request.BrandRequest;

@Mapper(componentModel = "spring")
public interface BrandMapper extends MapperContract<BrandDto, Brand, BrandRequest> {
    @Override
    BrandDto toDto(Brand brand);

    @Override
    Brand fromRequest(BrandRequest request);

    @Override
    Brand fromDto(BrandDto brandDto);
}
