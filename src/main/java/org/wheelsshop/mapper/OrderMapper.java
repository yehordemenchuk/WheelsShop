package org.wheelsshop.mapper;

import org.mapstruct.Mapper;
import org.wheelsshop.dto.OrderDto;
import org.wheelsshop.entities.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper extends EntityMapper<Order> {
    OrderDto toDto(Order order);
}
