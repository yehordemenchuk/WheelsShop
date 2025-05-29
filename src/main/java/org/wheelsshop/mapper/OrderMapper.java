package org.wheelsshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.wheelsshop.dto.OrderDto;
import org.wheelsshop.entities.Car;
import org.wheelsshop.entities.Order;
import org.wheelsshop.entities.User;
import org.wheelsshop.request.OrderRequest;

@Mapper(componentModel = "spring")
public abstract class OrderMapper implements MapperContract<OrderDto, Order, OrderRequest> {
    @Autowired
    private MapperHelper mapperHelper;

    @Mapping(target = "userId", source = "user", qualifiedByName = "mapUserId")
    @Mapping(target = "carId", source = "car", qualifiedByName = "mapCarId")
    @Override
    public abstract OrderDto toDto(Order order);

    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUser")
    @Mapping(target = "car", source = "carId", qualifiedByName = "mapCar")
    @Override
    public abstract Order fromRequest(OrderRequest request);

    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUser")
    @Mapping(target = "car", source = "carId", qualifiedByName = "mapCar")
    @Override
    public abstract Order fromDto(OrderDto orderDto);

    @Named("mapUser")
    public User mapUser(Long userId) {
        return mapperHelper.getUser(userId);
    }

    @Named("mapCar")
    public Car mapCar(Long carId) {
        return mapperHelper.getCar(carId);
    }

    @Named("mapUserId")
    public Long mapUserId(User user) {
        return mapperHelper.getUserId(user);
    }

    @Named("mapCarId")
    public Long mapCarId(Car car) {
        return mapperHelper.getCarId(car);
    }
}
