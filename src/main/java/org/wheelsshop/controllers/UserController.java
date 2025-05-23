package org.wheelsshop.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wheelsshop.dto.UserDto;
import org.wheelsshop.request.UserRequest;
import org.wheelsshop.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable long id) {
        return userService.findById(id);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserRequest request) throws BadRequestException {
        return userService.saveUser(request);
    }

    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable long id) {
        userService.deleteUserById(id);
    }
}
