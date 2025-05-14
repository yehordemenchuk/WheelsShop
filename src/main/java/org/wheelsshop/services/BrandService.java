package org.wheelsshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wheelsshop.entities.Brand;
import org.wheelsshop.repository.jpa.BrandJpaRepository;
import org.wheelsshop.repository.redis.BrandRedisRepository;

@Service
public class BrandService extends AbstractService<Brand> {
    @Autowired
    public BrandService(BrandJpaRepository brandJpaRepository,
                        BrandRedisRepository brandRedisRepository) {
        super(brandJpaRepository, brandRedisRepository, Brand.class);
    }
}
