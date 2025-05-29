package org.wheelsshop.repository.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.wheelsshop.dto.CarDto;
import org.wheelsshop.entities.Car;
import org.wheelsshop.mapper.CarMapper;
import org.wheelsshop.request.CarRequest;

@Repository
public class CarRedisRepository extends AbstractRedisRepository<CarDto, Car, CarRequest> {
    @Autowired
    public CarRedisRepository(RedisTemplate<String, Object> redisTemplate,
                              CarMapper carMapper) {

        super(redisTemplate, "car:", CarDto.class, carMapper);
    }
}
