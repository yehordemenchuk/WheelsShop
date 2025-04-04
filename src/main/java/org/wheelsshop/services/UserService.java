package org.wheelsshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wheelsshop.entities.User;
import org.wheelsshop.repository.UserJpaRepository;

@Service
public class UserService extends AbstractService<User> {
    @Autowired
    public UserService(UserJpaRepository repository) {
        super(repository);
    }
}
