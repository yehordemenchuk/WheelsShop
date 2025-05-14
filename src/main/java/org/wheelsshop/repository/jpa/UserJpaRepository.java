package org.wheelsshop.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wheelsshop.entities.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
