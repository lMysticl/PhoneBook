package com.mystic.controller;

import com.mystic.model.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pavel Putrenkov
 * @version 1.0
 * @since
 */
@RestController
@RequestMapping("/api")
public class Api {

    @RequestMapping(value = "/zalupa")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER') or hasAuthority('ADMIN_USER')")
    public User getUser() {
        User zalupa = new User();
        zalupa.setFirstname("Zalupa");
        return zalupa;
    }
}
