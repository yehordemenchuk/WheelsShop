package org.wheelsshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wheelsshop.dto.UserDto;
import org.wheelsshop.entities.User;
import org.wheelsshop.request.OrderRequest;
import org.wheelsshop.request.UserRequest;
import org.wheelsshop.services.MailSenderService;
import org.wheelsshop.services.OrderService;
import org.wheelsshop.services.UserService;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController extends AbstractController<UserDto, User, UserRequest> {
    private final OrderService orderService;
    private final MailSenderService mailSenderService;

    @Autowired
    public UserController(UserService userService,
                          OrderService orderService,
                          MailSenderService mailSenderService) {

        super(userService);

        this.orderService = orderService;

        this.mailSenderService = mailSenderService;
    }

    @PostMapping("/make-order")
    public void makeOrder(@RequestBody OrderRequest orderRequest) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {
        String userEmail = orderService.makeOrder(orderRequest);

        mailSenderService.sendOrderEmail(orderRequest, userEmail);
    }
}
