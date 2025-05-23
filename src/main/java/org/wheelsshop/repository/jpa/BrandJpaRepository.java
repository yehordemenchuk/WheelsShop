package org.wheelsshop.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wheelsshop.entities.Brand;

@Repository
public interface BrandJpaRepository extends JpaRepository<Brand, Long> {
    Brand findByName(String name);
}
