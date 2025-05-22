package org.wheelsshop.mapper;

import org.mapstruct.Mapper;
import org.wheelsshop.dto.CarDto;
import org.wheelsshop.entities.Car;

@Mapper(componentModel = "spring")
public interface CarMapper extends EntityMapper<Car> {
    CarDto toDto(Car car);
}
