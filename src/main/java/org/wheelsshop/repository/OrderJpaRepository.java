package org.wheelsshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wheelsshop.entities.Order;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}
