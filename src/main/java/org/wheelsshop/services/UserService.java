package org.wheelsshop.services;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wheelsshop.dto.UserDto;
import org.wheelsshop.entities.User;
import org.wheelsshop.exceptions.EntityNotFoundException;
import org.wheelsshop.mapper.UserMapper;
import org.wheelsshop.repository.jpa.UserJpaRepository;
import org.wheelsshop.repository.redis.UserRedisRepository;
import org.wheelsshop.request.UserRequest;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final UserRedisRepository userRedisRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserJpaRepository userJpaRepository,
                       UserRedisRepository userRedisRepository,
                       UserMapper userMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userRedisRepository = userRedisRepository;
        this.userMapper = userMapper;
    }

    public UserDto findById(Long id) {
        User user = userRedisRepository.getById(id);

        if (Objects.nonNull(user)) {
            return userMapper.UserToDto(user);
        }

        user = userJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        userRedisRepository.save(user, id);

        return userMapper.UserToDto(user);
    }

    public List<UserDto> findAllUsers() {
        return userJpaRepository.findAll().stream().map(userMapper::UserToDto).toList();
    }

    public UserDto saveUser(UserRequest userRequest) throws BadRequestException {
        if (userJpaRepository.findByEmail(userRequest.email()) != null) {
            throw new BadRequestException();
        }

        User user = userJpaRepository.save(userMapper.fromUserRequest(userRequest));

        return userMapper.UserToDto(user);
    }

    public void deleteUserById(Long id) {
        if (!userJpaRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        userJpaRepository.deleteById(id);

        userRedisRepository.deleteById(id);
    }
}
