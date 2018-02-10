package com.mystic.controller;

import com.mystic.model.dto.UserDTO;
import com.mystic.model.entity.Role;
import com.mystic.model.entity.User;
import com.mystic.model.pojos.UserRegistration;
import com.mystic.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Putrenkov Pavlo
 */
@RestController
@AllArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;


    @Autowired
    private TokenStore tokenStore;


    @PostMapping(value = "/register")
    public String register(@RequestBody UserRegistration userRegistration) {
        if (!userRegistration.getPassword().equals(userRegistration.getPasswordConfirmation()))
            return "Error the two passwords do not match";
        else if (userServiceImpl.getUser(userRegistration.getUsername()) != null)
            return "Error this username already exists";

        //Checking for non alphanumerical characters in the username.
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        if (pattern.matcher(userRegistration.getUsername()).find())
            return "No special characters are allowed in the username";

        userServiceImpl.save(new User(userRegistration.getUsername(), userRegistration.getPassword(), Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
        return "User created";
    }


    @GetMapping(value = "/autosingin")
    public UserDTO autoSingIn() {
        User user = userServiceImpl.getUser((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        ModelMapper modelMapper = new ModelMapper();

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;


    }


    @GetMapping(value = "/users")
    public List<User> users() {
        return userServiceImpl.getAllUsers();
    }

    @GetMapping(value = "/logouts")
    public void logout(@RequestParam(value = "access_token") String accessToken) {
        tokenStore.removeAccessToken(tokenStore.readAccessToken(accessToken));
    }

    @GetMapping(value = "/getUsername")
    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
