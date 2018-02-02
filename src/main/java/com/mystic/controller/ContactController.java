package com.mystic.controller;

import com.mystic.config.CustomUserDetails;
import com.mystic.model.dto.UserDTO;
import com.mystic.model.entity.Contact;
import com.mystic.model.entity.User;
import com.mystic.service.ContactService;
import com.mystic.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Putrenkov Pavlo
 */
@RestController
@AllArgsConstructor
public class ContactController {

    private final UserService userService;
    private final ContactService contactService;

    @Qualifier("getTokenStore")
    @Autowired
    private TokenStore tokenStore;

    @GetMapping(value = "/contacts/get-all",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity returnAllContact() {

        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(userDetails.getUsername());

        ModelMapper modelMapper = new ModelMapper();

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        List<Contact> contacts = contactService.getByUserId(userDTO.getUserId());

        if (contacts.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("User does not have contacts");
        } else {
            return ResponseEntity
                    .ok(contacts);
        }

    }

    @PostMapping(value = "contacts/add")
    public Contact addContact(Contact contact) {

        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(userDetails.getUsername());
        contact.setUserId(user.getUserId());

        return contactService.saveContact(contact);
    }

    @PostMapping(value = "contacts/delete")
    public void deleteContact(Contact contact) {
        contactService.deleteByUserId(contact.getContactId());
    }

    @PostMapping(value = "contacts/update")
    public void updateContact(Contact contact) {

        contactService.updateContact(contact);
    }
}
