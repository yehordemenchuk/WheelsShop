package org.wheelsshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wheelsshop.entities.User;
import org.wheelsshop.services.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends AbstractController<User> {
    @Autowired
    public UserController(UserService service) {
        super(service);
    }
}
