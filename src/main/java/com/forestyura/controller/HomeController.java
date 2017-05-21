package com.forestyura.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Putrenkov Pavlo
 */

@Controller
public class HomeController {

    @GetMapping(value = "/registration")
    public String registrationPage() {
        return "registration";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/")
    public String homePage() {
        return "index";
    }
}
