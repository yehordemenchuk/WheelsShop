package org.wheelsshop.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wheelsshop.entities.Car;

@Repository
public interface CarJpaRepository extends JpaRepository<Car, Long> {
}
