package org.wheelsshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wheelsshop.dto.UserDTO;
import org.wheelsshop.mapper.UserMapper;
import org.wheelsshop.services.UserService;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService service, UserMapper mapper) {
        this.userService = service;

        this.userMapper = mapper;
    }

    @PostMapping("/save")
    public void save(@RequestBody UserDTO userDTO) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {

        userService.save(userMapper.mapToUser(userDTO));
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable long id) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {

        return userMapper.mapToUserDTO(userService.findById(id));
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return userService
                .findAll()
                .stream()
                .map(userMapper::mapToUserDTO)
                .toList();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteById(id);
    }
}
