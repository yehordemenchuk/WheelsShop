package org.wheelsshop.repository.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.wheelsshop.dto.OrderDto;
import org.wheelsshop.entities.Order;
import org.wheelsshop.mapper.OrderMapper;
import org.wheelsshop.request.OrderRequest;

@Repository
public class OrderRedisRepository extends AbstractRedisRepository<OrderDto, Order, OrderRequest>{
    @Autowired
    public OrderRedisRepository(RedisTemplate<String, Object> redisTemplate,
                                OrderMapper orderMapper) {

        super(redisTemplate, "order:", OrderDto.class, orderMapper);
    }
}
