package org.wheelsshop.repository.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.wheelsshop.entities.Brand;

@Repository
public class BrandRedisRepository extends AbstractRedisRepository<Brand>{
    @Autowired
    public BrandRedisRepository(RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate, "brand:", Brand.class);
    }
}
