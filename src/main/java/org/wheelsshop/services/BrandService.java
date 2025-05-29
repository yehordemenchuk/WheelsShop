package org.wheelsshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wheelsshop.dto.BrandDto;
import org.wheelsshop.entities.Brand;
import org.wheelsshop.mapper.BrandMapper;
import org.wheelsshop.repository.jpa.BrandJpaRepository;
import org.wheelsshop.repository.redis.BrandRedisRepository;
import org.wheelsshop.request.BrandRequest;

import java.lang.reflect.InvocationTargetException;

@Service
public class BrandService extends AbstractService<BrandDto, Brand, BrandRequest> {
    @Autowired
    public BrandService(BrandJpaRepository brandJpaRepository,
                        BrandRedisRepository brandRedisRepository,
                        BrandMapper brandMapper) {
        super(brandJpaRepository, brandRedisRepository, Brand.class, brandMapper);
    }

    @Override
    public void save(BrandRequest request) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException, IllegalStateException {

        if (((BrandJpaRepository) getJpaRepository())
                .findByBrandName(request.brandName()) != null) {
            throw new IllegalStateException("Brand name already exists");
        }

        super.save(request);
    }
}
