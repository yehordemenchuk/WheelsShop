package org.wheelsshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wheelsshop.entities.User;
import org.wheelsshop.mapper.UserMapper;
import org.wheelsshop.repository.jpa.UserJpaRepository;
import org.wheelsshop.repository.redis.UserRedisRepository;
import org.wheelsshop.request.Request;
import org.wheelsshop.request.UserRequest;

import java.lang.reflect.InvocationTargetException;

@Service
public class UserService extends AbstractService<User> {
    @Autowired
    public UserService(UserJpaRepository userJpaRepository,
                       UserRedisRepository userRedisRepository,
                       UserMapper userMapper) {
        super(userJpaRepository, userRedisRepository, User.class, userMapper);
    }

    @Override
    public void save(Request<User> r) throws InvocationTargetException, NoSuchMethodException,
            IllegalAccessException {

        User user = ((UserMapper) getEntityMapper()).fromUserRequest(((UserRequest) r));

        getJpaRepository().save(user);

        getRedisRepository().deleteById(getEntityId(user));
    }
}
