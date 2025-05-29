package org.wheelsshop.repository.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.wheelsshop.dto.BrandDto;
import org.wheelsshop.entities.Brand;
import org.wheelsshop.mapper.BrandMapper;
import org.wheelsshop.request.BrandRequest;

@Repository
public class BrandRedisRepository extends AbstractRedisRepository<BrandDto, Brand, BrandRequest>{
    @Autowired
    public BrandRedisRepository(RedisTemplate<String, Object> redisTemplate,
                                BrandMapper brandMapper) {

        super(redisTemplate, "brand:", BrandDto.class, brandMapper);
    }
}
