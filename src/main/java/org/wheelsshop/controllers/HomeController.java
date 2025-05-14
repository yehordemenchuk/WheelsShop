package org.wheelsshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/api/v1/home")
    public String index() {
        return "Index";
    }

}