package com.forestyura.controller;

import com.forestyura.model.entity.User;
import com.forestyura.model.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Putrenkov Pavlo
 */
@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping(value = "/registration")
    public ResponseEntity registration(User user) {
        if (userService.existsUsername(user.getUsername())) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("User with this username already register");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
            return ResponseEntity
                    .ok("User successfully registered");

        }
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request, response, auth);
        return "redirect:/login?logout";
    }

}
