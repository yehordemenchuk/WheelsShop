package org.wheelsshop.mapper;

import org.mapstruct.Mapper;
import org.wheelsshop.dto.OrderDTO;
import org.wheelsshop.entities.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order mapToOrder(OrderDTO orderDTO);

    OrderDTO mapToOrderDTO(Order order);
}
