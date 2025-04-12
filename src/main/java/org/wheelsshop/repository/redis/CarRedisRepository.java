package org.wheelsshop.repository.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.wheelsshop.entities.Car;

@Repository
public class CarRedisRepository extends AbstractRedisRepository<Car> {
    @Autowired
    public CarRedisRepository(RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate, "car:", Car.class);
    }
}
