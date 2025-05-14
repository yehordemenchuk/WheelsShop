package org.wheelsshop.repository.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.wheelsshop.entities.Order;

@Repository
public class OrderRedisRepository extends AbstractRedisRepository<Order>{
    @Autowired
    public OrderRedisRepository(RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate, "order:", Order.class);
    }
}
