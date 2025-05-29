package org.wheelsshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wheelsshop.dto.UserDto;
import org.wheelsshop.entities.User;
import org.wheelsshop.mapper.UserMapper;
import org.wheelsshop.repository.jpa.UserJpaRepository;
import org.wheelsshop.repository.redis.UserRedisRepository;
import org.wheelsshop.request.UserRequest;

import java.lang.reflect.InvocationTargetException;

@Service
public class UserService extends AbstractService<UserDto, User, UserRequest> {
    @Autowired
    public UserService(UserJpaRepository userJpaRepository,
                       UserRedisRepository userRedisRepository,
                       UserMapper userMapper) {
        super(userJpaRepository, userRedisRepository, User.class, userMapper);
    }

    @Override
    public void save(UserRequest userRequest) throws InvocationTargetException, NoSuchMethodException,
            IllegalAccessException, IllegalStateException {

        UserJpaRepository repository = ((UserJpaRepository) getJpaRepository());

        if (repository.findByEmail(userRequest.email()).isPresent()) {
            throw new IllegalStateException("User with email already exists");
        }

        super.save(userRequest);
    }
}
