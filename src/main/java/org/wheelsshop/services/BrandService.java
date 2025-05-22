package org.wheelsshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wheelsshop.entities.Brand;
import org.wheelsshop.mapper.BrandMapper;
import org.wheelsshop.repository.jpa.BrandJpaRepository;
import org.wheelsshop.repository.redis.BrandRedisRepository;
import org.wheelsshop.request.BrandRequest;
import org.wheelsshop.request.Request;

import java.lang.reflect.InvocationTargetException;

@Service
public class BrandService extends AbstractService<Brand> {
    @Autowired
    public BrandService(BrandJpaRepository brandJpaRepository,
                        BrandRedisRepository brandRedisRepository,
                        BrandMapper brandMapper) {
        super(brandJpaRepository, brandRedisRepository, Brand.class, brandMapper);
    }

    @Override
    public void save(Request<Brand> r) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {

        Brand brand = ((BrandMapper) getEntityMapper()).fromBrandRequest(((BrandRequest) r));

        getJpaRepository().save(brand);

        getRedisRepository().deleteById(getEntityId(brand));
    }
}
