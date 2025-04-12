package org.wheelsshop.repository.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.wheelsshop.entities.User;

@Repository
public class UserRedisRepository extends AbstractRedisRepository<User> {
    @Autowired
    public UserRedisRepository(RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate, "user:", User.class);
    }
}
