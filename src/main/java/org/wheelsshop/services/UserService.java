package org.wheelsshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wheelsshop.entities.User;
import org.wheelsshop.repository.jpa.UserJpaRepository;
import org.wheelsshop.repository.redis.UserRedisRepository;

@Service
public class UserService extends AbstractService<User> {
    @Autowired
    public UserService(UserJpaRepository userJpaRepository,
                       UserRedisRepository userRedisRepository) {
        super(userJpaRepository, userRedisRepository, User.class);
    }
}
