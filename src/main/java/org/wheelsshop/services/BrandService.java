package org.wheelsshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wheelsshop.entities.Brand;
import org.wheelsshop.repository.BrandJpaRepository;

@Service
public class BrandService extends AbstractService<Brand> {
    @Autowired
    public BrandService(BrandJpaRepository repository) {
        super(repository);
    }
}
