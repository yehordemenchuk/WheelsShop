package org.wheelsshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import org.wheelsshop.dto.OrderDTO;
import org.wheelsshop.entities.Order;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order mapToOrder(OrderDTO orderDTO);

    OrderDTO mapToOrderDTO(Order order);
}
