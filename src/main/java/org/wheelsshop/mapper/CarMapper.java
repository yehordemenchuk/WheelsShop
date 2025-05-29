package org.wheelsshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.wheelsshop.dto.CarDto;
import org.wheelsshop.entities.Brand;
import org.wheelsshop.entities.Car;
import org.wheelsshop.entities.User;
import org.wheelsshop.request.CarRequest;

@Mapper(componentModel = "spring")
public abstract class CarMapper implements MapperContract<CarDto, Car, CarRequest> {
    @Autowired
    private MapperHelper mapperHelper;

    @Mapping(target = "userId", source = "user", qualifiedByName = "mapUserId")
    @Mapping(target = "brandId", source = "brand", qualifiedByName = "mapBrandId")
    @Override
    public abstract CarDto toDto(Car car);

    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUser")
    @Mapping(target = "brand", source = "brandId", qualifiedByName = "mapBrand")
    @Override
    public abstract Car fromRequest(CarRequest request);

    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUser")
    @Mapping(target = "brand", source = "brandId", qualifiedByName = "mapBrand")
    @Override
    public abstract Car fromDto(CarDto carDto);

    @Named("mapUser")
    public User mapUser(Long userId) {
        return mapperHelper.getUser(userId);
    }

    @Named("mapBrand")
    public Brand mapBrand(Long brandId) {
        return mapperHelper.getBrand(brandId);
    }

    @Named("mapBrandId")
    public Long mapBrandId(Brand brand) {
        return mapperHelper.getBrandId(brand);
    }

    @Named("mapUserId")
    public Long mapUserId(User user) {
        return mapperHelper.getUserId(user);
    }
}
