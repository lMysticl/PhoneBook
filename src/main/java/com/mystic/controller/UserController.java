package com.mystic.controller;

import com.mystic.model.entity.Role;
import com.mystic.model.entity.User;
import com.mystic.model.pojos.UserRegistration;
import com.mystic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class UserController {

    @Autowired
    private UserService userService;


//    @PostMapping(value = "/registration")
//    public ResponseEntity registration(User user) {
//        if (userService.existsUsername(user.getUsername())) {
//            return ResponseEntity
//                    .status(HttpStatus.NOT_ACCEPTABLE)
//                    .body("User with this username already register");
//        } else {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            userService.saveUser(user);
//            return ResponseEntity
//                    .ok("User successfully registered");
//
//        }
//    }
//
//    @GetMapping(value = "/logout")
//    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        new SecurityContextLogoutHandler().logout(request, response, auth);
//        return "redirect:/login?logout";
//    }


    @Qualifier("getTokenStore")
    @Autowired
    private TokenStore tokenStore;


    @PostMapping(value = "/register")
    public String register(@RequestBody UserRegistration userRegistration){
        if(!userRegistration.getPassword().equals(userRegistration.getPasswordConfirmation()))
            return "Error the two passwords do not match";
        else if(userService.getUser(userRegistration.getUsername()) != null)
            return "Error this username already exists";

        //Checking for non alphanumerical characters in the username.
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        if(pattern.matcher(userRegistration.getUsername()).find())
            return "No special characters are allowed in the username";

        userService.save(new User(userRegistration.getUsername(), userRegistration.getPassword(), Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
        return "User created";
    }

    @GetMapping(value = "/users")
    public List<User> users(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/logouts")
    public void logout(@RequestParam(value = "access_token") String accessToken){
        tokenStore.removeAccessToken(tokenStore.readAccessToken(accessToken));
    }

    @GetMapping(value ="/getUsername")
    public String getUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
