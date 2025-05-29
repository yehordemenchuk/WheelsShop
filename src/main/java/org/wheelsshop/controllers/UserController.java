package org.wheelsshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wheelsshop.dto.UserDto;
import org.wheelsshop.entities.User;
import org.wheelsshop.request.UserRequest;
import org.wheelsshop.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController extends AbstractController<UserDto, User, UserRequest> {
    @Autowired
    public UserController(UserService userService) {
        super(userService);
    }
}
