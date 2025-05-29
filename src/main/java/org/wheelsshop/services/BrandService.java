package org.wheelsshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wheelsshop.dto.BrandDto;
import org.wheelsshop.entities.Brand;
import org.wheelsshop.mapper.BrandMapper;
import org.wheelsshop.repository.jpa.BrandJpaRepository;
import org.wheelsshop.repository.redis.BrandRedisRepository;
import org.wheelsshop.request.BrandRequest;

@Service
public class BrandService extends AbstractService<BrandDto, Brand, BrandRequest> {
    @Autowired
    public BrandService(BrandJpaRepository brandJpaRepository,
                        BrandRedisRepository brandRedisRepository,
                        BrandMapper brandMapper) {
        super(brandJpaRepository, brandRedisRepository, Brand.class, brandMapper);
    }
}
