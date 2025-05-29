package org.wheelsshop.repository.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.wheelsshop.dto.UserDto;
import org.wheelsshop.entities.User;
import org.wheelsshop.mapper.UserMapper;
import org.wheelsshop.request.UserRequest;

@Repository
public class UserRedisRepository extends AbstractRedisRepository<UserDto, User, UserRequest> {
    @Autowired
    public UserRedisRepository(RedisTemplate<String, Object> redisTemplate,
                               UserMapper userMapper) {

        super(redisTemplate, "user:", UserDto.class, userMapper);
    }
}
